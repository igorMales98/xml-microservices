package com.xml.repository;

import com.xml.enummeration.RentRequestStatus;
import com.xml.model.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {
    List<RentRequest> findByRentRequestStatus(RentRequestStatus rentRequestStatus);

    @Query(value = "SELECT * FROM rent_request WHERE rent_request.advertiser_id = :id", nativeQuery = true)
    List<RentRequest> findByAdvertiserId(Long id);

    List<RentRequest> findByCustomerId(Long customerId);

    @Query(value = "SELECT COUNT(ra.rent_request_id) FROM rented_advertisements ra WHERE ra.advertisement_id = :advertisementId", nativeQuery = true)
    Integer getTimesRented(Long advertisementId);

    RentRequest findTopByOrderByIdDesc();
}
