package com.xml.service.impl;

import com.xml.dto.MessageDto;
import com.xml.dto.UserDto;
import com.xml.feignClients.RentRequestFeignClient;
import com.xml.feignClients.UserFeignClient;
import com.xml.model.Message;
import com.xml.repository.MessageRepository;
import com.xml.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RentRequestFeignClient rentRequestFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    public MessageServiceImpl() {
    }

    @Override
    public List<UserDto> getPeople(Long id, String token) {
        List<Long> peopleLong = this.rentRequestFeignClient.getPeople(id, token);
        //history
        for (Message message : this.messageRepository.findAll()) {
            if (message.getSenderId().equals(id)) {
                if (!peopleLong.contains(message.getReceiverId())) {
                    peopleLong.add(message.getReceiverId());
                }
            }
            if (message.getReceiverId().equals(id)) {
                if (!peopleLong.contains(message.getSenderId())) {
                    peopleLong.add(message.getSenderId());
                }
            }
        }
        List<UserDto> people = new ArrayList<>();
        for (Long personLong : peopleLong) {
            if (personLong!=id) {
                people.add(this.userFeignClient.getUserById(personLong, token));
            }
            System.out.println("ima: "+personLong);
        }
        return people;
    }

    @Override
    public List<Message> getMessages(Long agentId, Long customerId) {
        List<Message> messages = messageRepository.findAllByAgentIdAndCustomerId(agentId, customerId);
        messages.sort(Comparator.comparing(Message::getId));
        return messages;
    }

    @Override
    public void sendMessage(MessageDto messageDto) throws ParseException {
        Message message = new Message();
        message.setMessage(messageDto.getMessage());
        message.setReceiverId(messageDto.getReceiver().getId());
        message.setSenderId(messageDto.getSender().getId());
        message.setMessageDate(LocalDateTime.now());
        messageRepository.save(message);
    }
}
