package com.xml.repository;

import com.xml.model.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarClassRepository extends JpaRepository<CarClass, Long> {

}
