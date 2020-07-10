package com.xml.repository;

import com.xml.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "SELECT * FROM report r WHERE r.car_id = :carId", nativeQuery = true)
    List<Report> getAllReportsForACar(@Param("carId") Long carId);
}
