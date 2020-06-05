package com.xml.repository;

import com.xml.model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

    @Query(value = "SELECT * FROM registration_request WHERE registration_request.id = :id", nativeQuery = true)
    RegistrationRequest findOneById(Long id);
}
