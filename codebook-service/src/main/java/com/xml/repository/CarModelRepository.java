package com.xml.repository;

import com.xml.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> getByCarBrandId(Long brandId);

    CarModel getByName(String name);
}
