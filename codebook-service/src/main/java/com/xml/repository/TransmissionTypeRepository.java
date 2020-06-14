package com.xml.repository;

import com.xml.model.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {

}
