package com.xml.service;

import com.xml.dto.MessageDto;
import com.xml.model.Message;

import java.text.ParseException;
import java.util.List;

public interface MessageService {
    List<Long> getReservedCustomers(String username);

    List<Message> getMessages(Long agentId, Long customerId);

    void sendMessage(MessageDto messageDto) throws ParseException;
}
