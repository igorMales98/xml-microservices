package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.RentRequestDto;
import com.xml.dto.UserDto;
import com.xml.enummeration.RentRequestStatus;
import com.xml.feignClients.AdvertisementFeignClient;
import com.xml.feignClients.UserFeignClient;
import com.xml.model.Email;
import com.xml.model.EmailBinding;
import com.xml.model.RentRequest;
import com.xml.model.Report;
import com.xml.repository.RentRequestRepository;
import com.xml.repository.ReportRepository;
import com.xml.service.RentRequestService;
import com.xml.soap.code.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RentRequestServiceImpl implements RentRequestService {

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private AdvertisementFeignClient advertisementFeignClient;

    @Autowired
    private ReportRepository reportRepository;

    private MessageChannel messageChannel;

    public RentRequestServiceImpl(EmailBinding emailBinding) {
        messageChannel = emailBinding.sendMail();
    }

    @Override
    public void createRentRequest(RentRequestDto rentRequestDto, String token) {
        System.out.println("Token je: " + token);
        if (rentRequestDto.isPhysicalRent()) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(rentRequestDto.getCustomer().getFirstName());
            userDto.setLastName(rentRequestDto.getCustomer().getLastName());
            userDto.setEmail(rentRequestDto.getCustomer().getEmail());
            userDto.setCountry(rentRequestDto.getCustomer().getCountry());
            userDto.setCity(rentRequestDto.getCustomer().getCity());
            userDto.setAddress(rentRequestDto.getCustomer().getAddress());
            userDto.setPhone(rentRequestDto.getCustomer().getPhone());
            userDto.setEnabled(false);
            Long customerId = this.userFeignClient.createPhysicalUser(userDto, token);
            userDto.setId(customerId);
            rentRequestDto.setCustomer(userDto);
        }

        if (!rentRequestDto.isBundle()) {
            for (AdvertisementDto advertisementDto : rentRequestDto.getAdvertisementsForRent()) {
                RentRequest newRequest = this.createRequest(rentRequestDto, advertisementDto.getId(), false, token);
                AdvertisementDto advertisementDto1 = this.advertisementFeignClient.getOne(advertisementDto.getId(), token);
                newRequest.setAdvertiserId(advertisementDto1.getAdvertiser().getId());
                newRequest.setCreated(LocalDateTime.now());
                System.out.println("Advertiser: " + advertisementDto1.getAdvertiser().getId());
                this.rentRequestRepository.save(newRequest);
            }
        } else {
            List<Long> temp = new ArrayList<>();
            for (AdvertisementDto advertisementDto : rentRequestDto.getAdvertisementsForRent()) {
                if (!temp.contains(advertisementDto.getAdvertiser().getId())) {
                    temp.add(advertisementDto.getAdvertiser().getId());
                }
            }

            for (Long id : temp) {
                RentRequest newRequest = this.createRequest(rentRequestDto, id, true, token);
                AdvertisementDto advertisementDto1 = this.advertisementFeignClient.getOne(id, token);
                newRequest.setAdvertiserId(advertisementDto1.getAdvertiser().getId());
                System.out.println(advertisementDto1.getAdvertiser().getId());
                newRequest.setCreated(LocalDateTime.now());
                this.rentRequestRepository.save(newRequest);
            }

        }

    }

    @Override
    public List<Long> getPeople(Long id, String token) {
        List<RentRequest> reservedRequests = this.rentRequestRepository.findByRentRequestStatus(RentRequestStatus.RESERVED);
        List<RentRequest> customersRequests = new ArrayList<>();
        List<Long> customersAds = new ArrayList<>();
        //kada je logovan customer
        for (RentRequest reservedRequest : reservedRequests) {
            if (reservedRequest.getCustomerId().equals(id)) {
                customersRequests.add(reservedRequest);
            }
        }
        List<AdvertisementDto> allAdvertisements = this.advertisementFeignClient.getAll(token);
        List<Long> advertisers = new ArrayList<>();
        for (RentRequest rentRequest : customersRequests) {
            for (Long advertisementForRent : rentRequest.getAdvertisementsForRent())
                if (!customersAds.contains(advertisementForRent)) {
                    customersAds.add(advertisementForRent);
                }
        }
        for (Long adId : customersAds) {
            for (AdvertisementDto advertisementDto : allAdvertisements) {
                if (adId.equals(advertisementDto.getId())) {
                    if (!advertisers.contains(advertisementDto.getAdvertiser().getId())) {
                        advertisers.add(advertisementDto.getAdvertiser().getId());
                    }
                }
            }
        }
        //kad je logovan agent
        for (RentRequest reservedRequest : reservedRequests) {
            for (Long advertisementForRent : reservedRequest.getAdvertisementsForRent()) {
                AdvertisementDto reservedAd = this.advertisementFeignClient.getOne(advertisementForRent, token);
                if (reservedAd.getAdvertiser().getId().equals(id)) {
                    if (!advertisers.contains(reservedRequest.getCustomerId()))
                        advertisers.add(reservedRequest.getCustomerId());
                }
            }
        }
        return advertisers;
    }

    @Override
    public List<RentRequestDto> getPaidRentRequests(String token) {
        List<RentRequest> reservedRentRequests = this.rentRequestRepository.findByRentRequestStatus(RentRequestStatus.PAID);

        List<RentRequestDto> rentRequestDtos = new ArrayList<>();

        for (RentRequest request : reservedRentRequests) {
            RentRequestDto rentRequestDto = new RentRequestDto();
            rentRequestDto.setId(request.getId());
            rentRequestDto.setReservedFrom(request.getReservedFrom());
            rentRequestDto.setReservedTo(request.getReservedTo());
            Set<AdvertisementDto> advertisementDtos = new HashSet<>();
            for (Long advId : request.getAdvertisementsForRent()) {
                AdvertisementDto advertisementDto = new AdvertisementDto();
                advertisementDto.setId(advId);
                advertisementDtos.add(advertisementDto);
            }
            rentRequestDto.setAdvertisementsForRent(advertisementDtos);
            rentRequestDtos.add(rentRequestDto);
        }

        return rentRequestDtos;
    }

    @Override
    public List<RentRequestDto> getCustomerRentRequests(String token, Long id) {
        System.out.println("token u rentu je" + token);
        List<RentRequest> rentRequests = this.rentRequestRepository.findByCustomerId(id);
        List<RentRequestDto> rentRequestDtos = new ArrayList<>();
        for (RentRequest request : rentRequests) {
            createDtoFromEntity(token, rentRequestDtos, request);
        }
        return rentRequestDtos;
    }

    @Override
    public List<RentRequestDto> getCustomerPendingRentRequests(String token, Long id) {
        List<RentRequest> rentRequests = this.rentRequestRepository.findByCustomerId(id);
        List<RentRequestDto> rentRequestDtos = new ArrayList<>();
        for (RentRequest request : rentRequests) {
            if (request.getRentRequestStatus().equals(RentRequestStatus.PENDING)) {
                createDtoFromEntity(token, rentRequestDtos, request);
            }
        }
        return rentRequestDtos;
    }

    @Override
    public Integer getTimesRented(Long advertisementId) {
        return this.rentRequestRepository.getTimesRented(advertisementId);
    }

    @Override
    public float getRentMileage(Long advertisementId) {
        List<RentRequest> rentRequests = this.rentRequestRepository.findAll();
        List<Report> reports = new ArrayList<>();
        for (RentRequest request : rentRequests) {
            if (request.getAdvertisementsForRent().contains(advertisementId)) {
                List<Report> reportList = this.reportRepository.findByRentRequestId(request.getId());
                reports.addAll(reportList);
            }
        }
        float mileage = 0;
        for (Report report : reports) {
            mileage += report.getKm();
        }
        return mileage;
    }

    private void createDtoFromEntity(String token, List<RentRequestDto> rentRequestDtos, RentRequest request) {
        RentRequestDto rentRequestDto = new RentRequestDto();
        rentRequestDto.setId(request.getId());
        rentRequestDto.setReservedFrom(request.getReservedFrom());
        rentRequestDto.setReservedTo(request.getReservedTo());
        rentRequestDto.setRentRequestStatus(request.getRentRequestStatus());
        Set<AdvertisementDto> advertisementDtos = new HashSet<>();
        for (Long advId : request.getAdvertisementsForRent()) {
            AdvertisementDto advertisementDto = advertisementFeignClient.getOne(advId, token);
            advertisementDtos.add(advertisementDto);
        }
        rentRequestDto.setAdvertisementsForRent(advertisementDtos);
        UserDto customer = this.userFeignClient.getUserById(request.getCustomerId(), token);
        rentRequestDto.setCustomer(customer);
        rentRequestDtos.add(rentRequestDto);
    }

    private RentRequest createRequest(RentRequestDto rentRequestDto, Long id, boolean bundle, String token) {
        RentRequest newRequest = new RentRequest();
        if (rentRequestDto.isPhysicalRent()) {
            newRequest.setRentRequestStatus(RentRequestStatus.PAID);
        } else {
            newRequest.setRentRequestStatus(RentRequestStatus.PENDING);
        }
        newRequest.setReservedTo(rentRequestDto.getReservedTo());
        newRequest.setReservedFrom(rentRequestDto.getReservedFrom());

        Set<Long> newSet = new HashSet<>();
        if (!bundle) {
            newSet.add(id);
        } else {
            for (AdvertisementDto advertisementDto : rentRequestDto.getAdvertisementsForRent()) {
                if (advertisementDto.getAdvertiser().getId().equals(id)) {
                    newSet.add(advertisementDto.getId());
                }
            }
        }

        newRequest.setAdvertisementsForRent(newSet);
        newRequest.setCustomerId(rentRequestDto.getCustomer().getId());
        newRequest.setReports(new HashSet<>());
        this.declineRequests(rentRequestDto);
        return newRequest;
    }

    private void declineRequests(RentRequestDto rentRequestDto) {
        if (rentRequestDto.isPhysicalRent()) {
            Set<AdvertisementDto> advertisementSet = new HashSet<>(rentRequestDto.getAdvertisementsForRent());
            List<RentRequest> rentRequests = this.rentRequestRepository.findAll();
            for (RentRequest request : rentRequests) {
                if (request.getRentRequestStatus().equals(RentRequestStatus.PENDING) && !checkDates(rentRequestDto, request)
                ) {
                    for (Long advertisementId : request.getAdvertisementsForRent()) {
                        for (AdvertisementDto a : advertisementSet) {
                            if (a.getId().equals(advertisementId)) {
                                request.setRentRequestStatus(RentRequestStatus.CANCELED);
                                this.rentRequestRepository.save(request);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkDates(RentRequestDto rentRequestDto, RentRequest request) {
        return (rentRequestDto.getReservedTo().isBefore(request.getReservedFrom()) || rentRequestDto.getReservedFrom().isAfter(request.getReservedTo()));
    }

    @Override
    public List<RentRequestDto> getUserRentRequests(Long id, String token) {
        List<RentRequestDto> rentRequestDtos = new ArrayList<>();

        List<RentRequest> allRequests = this.rentRequestRepository.findByAdvertiserId(id);
        return getUserRentRequestsDtos(rentRequestDtos, allRequests, token);
    }

    private List<RentRequestDto> getUserRentRequestsDtos(List<RentRequestDto> rentRequestDtos, List<RentRequest> allRequests, String token) {
        for (RentRequest request : allRequests) {
            if (!request.getRentRequestStatus().equals(RentRequestStatus.RESERVED)) {
                if (request.getCreated().isBefore(LocalDateTime.now().minusHours(24L)) && request.getRentRequestStatus().equals(RentRequestStatus.PENDING)) {
                    RentRequestDto requestDto = new RentRequestDto();
                    requestDto.setId(request.getId());
                    RentRequest temp = this.rentRequestRepository.findById(request.getId()).get();
                    temp.setRentRequestStatus(RentRequestStatus.CANCELED);

                    this.rentRequestRepository.save(temp);

                } else if (request.getRentRequestStatus().equals(RentRequestStatus.PENDING)) {
                    RentRequestDto requestDto = new RentRequestDto();
                    requestDto.setId(request.getId());
                    RentRequest temp = this.rentRequestRepository.findById(request.getId()).get();
                    temp.setRentRequestStatus(request.getRentRequestStatus());
                    this.rentRequestRepository.save(temp);
                    requestDto.setReservedFrom(request.getReservedFrom());
                    requestDto.setReservedTo(request.getReservedTo());
                    requestDto.setRentRequestStatus(request.getRentRequestStatus());

                    UserDto customer = this.userFeignClient.getUserById(request.getCustomerId(), token);
                    requestDto.setCustomer(customer);

                    Set<AdvertisementDto> advertisementDtos = new HashSet<>();
                    for (Long advertisementId : request.getAdvertisementsForRent()) {
                        advertisementDtos.add(this.advertisementFeignClient.getOne(advertisementId, token));
                    }

                    requestDto.setAdvertisementsForRent(advertisementDtos);

                    rentRequestDtos.add(requestDto);
                }
            }
        }

        return rentRequestDtos;
    }

    @Override
    public void cancelRentRequest(Long id, String token) {
        RentRequest rentRequest = this.rentRequestRepository.findById(id).get();
        rentRequest.setRentRequestStatus(RentRequestStatus.CANCELED);
        sendEmails(token, rentRequest);
    }

    @Override
    public void acceptRentRequest(Long id, String token) {
        RentRequest rentRequest = this.rentRequestRepository.findById(id).get();
        rentRequest.setRentRequestStatus(RentRequestStatus.PAID);

        sendEmails(token, rentRequest);
    }

    private void sendEmails(String token, RentRequest rentRequest) {
        UserDto advertiser = this.userFeignClient.getUserById(rentRequest.getAdvertiserId(), token);
        UserDto customer = this.userFeignClient.getUserById(rentRequest.getCustomerId(), token);

        Email email = new Email(advertiser.getEmail(), "Cancelled rent request.", "Rent request has been cancelled.");
        Email email2 = new Email(customer.getEmail(), "Cancelled rent request.", "Rent request has been cancelled.");

        Message<Email> message = MessageBuilder.withPayload(email).build();
        Message<Email> message2 = MessageBuilder.withPayload(email2).build();

        this.messageChannel.send(message);
        this.messageChannel.send(message2);

        this.rentRequestRepository.save(rentRequest);
    }
}
