package com.xml.controller;

import com.xml.dto.MessageDto;
import com.xml.dto.UserDto;
import com.xml.mapper.MessageDtoMapper;
import com.xml.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageDtoMapper messageDtoMapper;

    @GetMapping(value = "/test")
    @PreAuthorize("hasAuthority('TEST')")
    public String test() {
        System.out.println("iz message " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "Hello svet";
    }

    @GetMapping(value = "/getPeople/{id}")
    public ResponseEntity<?> getPeople(@RequestHeader("Authorization") String token,@PathVariable("id") Long id) {
        try {
            List<UserDto> users = this.messageService.getPeople(id,token);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getMessages/{agentId}/{customerId}")
    public ResponseEntity<?> getMessages(@PathVariable("agentId") Long agentId, @PathVariable("customerId") Long customerId) {
        try {
            List<MessageDto> messageDtos = this.messageService.getMessages(agentId, customerId).stream()
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
            this.messageService.sendMessage(messageDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
