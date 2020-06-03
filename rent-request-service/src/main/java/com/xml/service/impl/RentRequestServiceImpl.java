package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.RentRequestDto;
import com.xml.enummeration.RentRequestStatus;
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

    @Override
    public void createRentRequest(RentRequestDto rentRequestDto, String token) {
        if (!rentRequestDto.isBundle()) {
            for (AdvertisementDto advertisementDto : rentRequestDto.getAdvertisementsForRent()) {
                RentRequest newRequest = this.createRequest(rentRequestDto, advertisementDto.getId(), false);
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
                RentRequest newRequest = this.createRequest(rentRequestDto, id, true);
                this.rentRequestRepository.save(newRequest);
            }

        }

    }

    private RentRequest createRequest(RentRequestDto rentRequestDto, Long id, boolean bundle) {
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
        // TODO: ne treba menjati na canceled one koji nisu u opsegu zadatog datuma
        if (rentRequestDto.isPhysicalRent()) {
            Set<AdvertisementDto> advertisementSet = new HashSet<>(rentRequestDto.getAdvertisementsForRent());
            List<RentRequest> rentRequests = this.rentRequestRepository.findAll();
            for (RentRequest request : rentRequests) {
                if (request.getRentRequestStatus().equals(RentRequestStatus.PENDING)) {
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
}