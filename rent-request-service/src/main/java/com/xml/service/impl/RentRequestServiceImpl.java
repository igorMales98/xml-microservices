package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.RentRequestDto;
import com.xml.dto.UserDto;
import com.xml.enummeration.RentRequestStatus;
import com.xml.feignClients.AdvertisementFeignClient;
import com.xml.feignClients.UserFeignClient;
import com.xml.model.RentRequest;
import com.xml.repository.RentRequestRepository;
import com.xml.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void createRentRequest(RentRequestDto rentRequestDto, String token) {
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

        if (!rentRequestDto.isBundle()) {
            for (AdvertisementDto advertisementDto : rentRequestDto.getAdvertisementsForRent()) {
                RentRequest newRequest = this.createRequest(rentRequestDto, advertisementDto.getId(), false, token);
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
            for (Long advertisementForRent: rentRequest.getAdvertisementsForRent())
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
            for (Long advertisementForRent: reservedRequest.getAdvertisementsForRent()) {
                AdvertisementDto reservedAd = this.advertisementFeignClient.getOne(advertisementForRent,token);
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
        List<RentRequest> rentRequests = this.rentRequestRepository.findByCustomerId(id);
        List<RentRequestDto> rentRequestDtos = new ArrayList<>();
        for (RentRequest request : rentRequests) {
            RentRequestDto rentRequestDto = new RentRequestDto();
            rentRequestDto.setId(request.getId());
            rentRequestDto.setReservedFrom(request.getReservedFrom());
            rentRequestDto.setReservedTo(request.getReservedTo());
            Set<AdvertisementDto> advertisementDtos = new HashSet<>();
            for (Long advId : request.getAdvertisementsForRent()) {
                AdvertisementDto advertisementDto = advertisementFeignClient.getOne(advId,token);
                advertisementDtos.add(advertisementDto);
            }
            rentRequestDto.setAdvertisementsForRent(advertisementDtos);
            rentRequestDtos.add(rentRequestDto);
        }
        return rentRequestDtos;
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
}
