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

    // TODO: popraviti query, ne spajati tabele zbog deljenja baze
    // TODO: popraviti data.sql fajlove

    @Query(value = "SELECT * FROM advertisement a " +
            "WHERE (:dateFrom BETWEEN a.available_from AND a.available_to) AND (:dateTo BETWEEN a.available_from AND a.available_to)", nativeQuery = true)
    List<Advertisement> basicSearch(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

    @Query(value = "SELECT * FROM advertisement a " +
            "WHERE a.advertiser_id = :id AND (:dateFrom BETWEEN a.available_from AND a.available_to) AND (:dateTo BETWEEN a.available_from AND a.available_to)", nativeQuery = true)
    List<Advertisement> basicSearchForMyAdvertisements(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo, @Param("id") Long id);
}
