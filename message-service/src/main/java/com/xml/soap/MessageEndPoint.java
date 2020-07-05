package com.xml.soap;

import com.xml.dto.MessageDto;
import com.xml.service.MessageService;
import com.xml.soap.code.MessageRequest;
import com.xml.soap.code.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.ParseException;
import java.time.LocalDateTime;

@Endpoint
public class MessageEndPoint {

    @Autowired
    private MessageService messageService;

    private static final String NAMESPACE_URI = "http://localhost:8088/message-service-schema";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "messageRequest")
    @ResponsePayload
    public MessageResponse createPricelist(@RequestPayload MessageRequest request) throws ParseException {
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

}
