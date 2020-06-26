package com.xml.controller;

import com.xml.dto.MessageDto;
import com.xml.dto.UserDto;
import com.xml.mapper.MessageDtoMapper;
import com.xml.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageDtoMapper messageDtoMapper;

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @GetMapping(value = "/test")
    @PreAuthorize("hasAuthority('TEST')")
    public String test() {
        System.out.println("iz message " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "Hello svet";
    }

    @GetMapping(value = "/people/{id}")
    @PreAuthorize("hasAuthority('PEOPLE_FOR_CHAT')")
    public ResponseEntity<?> getPeople(@RequestHeader("Authorization") String token,@PathVariable("id") Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username : {} tried to get people.", LocalDateTime.now(), userDetails.getUsername());
            List<UserDto> users = this.messageService.getPeople(id,token);
            logger.info("Date : {}, Successfully returned list of people.", LocalDateTime.now());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get people. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{agentId}/{customerId}")
    @PreAuthorize("hasAuthority('READ_MESSAGES')")
    public ResponseEntity<?> getMessages(@PathVariable("agentId") Long agentId, @PathVariable("customerId") Long customerId) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username : {} tried to get messages.", LocalDateTime.now(), userDetails.getUsername());
            List<MessageDto> messageDtos = this.messageService.getMessages(agentId, customerId).stream()
                    .map(messageDtoMapper::toDto).collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of messages.", LocalDateTime.now());
            return new ResponseEntity<>(messageDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get messages. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_MESSAGE')")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username : {} tried to sent message.", LocalDateTime.now(), userDetails.getUsername());
            this.messageService.sendMessage(messageDto);
            logger.info("Date : {}, A user with username: {} has successfully sent message.", LocalDateTime.now(), userDetails.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to sent message. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
