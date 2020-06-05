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
        List<Long> peopleLong = this.rentRequestFeignClient.getPeople(id,token);
        List<UserDto> people = new ArrayList<>();
        for (Long personLong : peopleLong) {
            people.add(this.userFeignClient.getUserById(id,token));
        }
        return people;
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
