package com.xml.service.impl;

import com.xml.dto.MessageDto;
import com.xml.model.Message;
import com.xml.repository.MessageRepository;
import com.xml.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Long> getReservedCustomers(String username) {
        return null;
    }

    @Override
    public List<Message> getMessages(Long agentId, Long customerId) {
        List<Message> messages = messageRepository.findAllByAgentIdAndCustomerId(agentId,customerId);
        messages.sort(Comparator.comparing(Message::getId));
        return messages;
    }

    @Override
    public void sendMessage(MessageDto messageDto) throws ParseException {

    }
}
