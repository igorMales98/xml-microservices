package com.xml.controller;

import com.xml.dto.MessageDto;
import com.xml.mapper.MessageDtoMapper;
import com.xml.security.TokenUtils;
import com.xml.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "https://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController
{
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageDtoMapper messageDtoMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/getReservedCustomers/{token}")
    public ResponseEntity<?> getReservedCustomers(@RequestHeader("Authorization") String token) {
        try {
            String username = tokenUtils.getUsernameFromToken(token.substring(7));
            System.out.println("user"+username);
            List<Long> users = this.messageService.getReservedCustomers(username);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/getMessages/{agentId}/{customerId}")
    public ResponseEntity<?> getMessages(@PathVariable("agentId") Long agentId, @PathVariable("customerId") Long customerId) {
        try {
            List<MessageDto> messageDtos = this.messageService.getMessages(agentId,customerId).stream()
                    .map(messageDtoMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(messageDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        try {
            //TODO: sender-senderId
            this.messageService.sendMessage(messageDto);
            System.out.println("usao1");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("usao2");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
