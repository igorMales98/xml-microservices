package com.xml.repository;

import com.xml.model.AdditionalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalBillRepository extends JpaRepository<AdditionalBill, Long> {
}
