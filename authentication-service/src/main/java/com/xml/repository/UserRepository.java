package com.xml.repository;

import com.xml.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value="SELECT * FROM users WHERE users.deleted = 0 AND users.type LIKE %:customer%", nativeQuery = true)
    List<User> findAllCustomers(@Param("customer") String customer);
}
