package com.xml.dto;


import java.time.LocalDateTime;

public class MessageDto {
    private Long id;

    private UserDto sender;

    private UserDto receiver;

    private String message;

    private LocalDateTime messageDate;

    private Long senderId;

    private Long receiverId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public UserDto getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDto receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public Long getSenderId() { return senderId; }

    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getReceiverId() { return receiverId; }

    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
}
