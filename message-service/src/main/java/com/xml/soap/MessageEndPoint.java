package com.xml.soap;

import com.xml.dto.MessageDto;
import com.xml.model.Message;
import com.xml.repository.MessageRepository;
import com.xml.service.MessageService;
import com.xml.soap.code.GetMessagesRequest;
import com.xml.soap.code.GetMessagesResponse;
import com.xml.soap.code.MessageRequest;
import com.xml.soap.code.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Endpoint
public class MessageEndPoint {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    private static final String NAMESPACE_URI = "http://localhost:8088/message-service-schema";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "messageRequest")
    @ResponsePayload
    public MessageResponse sendMessage(@RequestPayload MessageRequest request) throws ParseException {
        System.out.println("SOAP - Send message.");

        MessageResponse response = new MessageResponse();
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(request.getMessage());
        messageDto.setMessageDate(LocalDateTime.parse(request.getMessageDate()));
        messageDto.setSenderId(request.getSenderId());
        messageDto.setReceiverId(request.getRecieverId());

        Long id = this.messageService.send(messageDto);
        response.setMessageId(id);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getAllMessages(@RequestPayload GetMessagesRequest request) throws ParseException {
        System.out.println("SOAP - Get All Messages");

        GetMessagesResponse response = new GetMessagesResponse();
        List<Message> messages = this.messageRepository.findAll();

        for(Message message : messages){
            if(message.getReceiverId().equals(request.getSenderId())) {
                com.xml.soap.code.Message messageSoap = new com.xml.soap.code.Message();
                messageSoap.setId(message.getId());
                messageSoap.setMessage(message.getMessage());
                messageSoap.setMessageDate(message.getMessageDate().toString());
                messageSoap.setRecieverId(message.getReceiverId());
                messageSoap.setSenderId(message.getSenderId());

                response.getMessage().add(messageSoap);
            }
        }

        return response;
    }

}
