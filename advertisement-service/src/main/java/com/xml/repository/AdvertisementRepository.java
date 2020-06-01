package com.xml.repository;

import com.xml.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> getAllByAdvertiserId(Long userId);


    @Query(value = "SELECT * FROM advertisement a " +
            "WHERE (:dateFrom BETWEEN a.available_from AND a.available_to) AND (:dateTo BETWEEN a.available_from AND a.available_to)" +
            "AND a.id NOT IN " +
            "(SELECT ra.advertisement_id FROM rented_advertisements ra " +
            "WHERE ra.rent_request_id IN " +
            "(SELECT rr.id FROM rent_request rr " +
            "WHERE (:dateFrom BETWEEN rr.reserved_from AND rr.reserved_to) OR (:dateTo BETWEEN rr.reserved_from AND rr.reserved_to) AND rr.status = 'RESERVED' ))", nativeQuery = true)
    List<Advertisement> basicSearch(@Param("dateFrom")LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);
}
