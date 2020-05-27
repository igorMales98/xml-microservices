package com.xml.repository;

import com.xml.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM message m WHERE (m.receiver_id = :agentId AND m.sender_id = :customerId) OR (m.sender_id = :agentId AND m.receiver_id = :customerId)", nativeQuery = true)
    List<Message> findAllByAgentIdAndCustomerId(Long agentId, Long customerId);
}
