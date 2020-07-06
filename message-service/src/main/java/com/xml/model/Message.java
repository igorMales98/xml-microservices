package com.xml.model;

import com.xml.adapter.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", namespace = "http://localhost:8088/message-service-schema")
@XmlRootElement(name = "messageClass")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column
    @XmlElement
    private Long senderId;

    @Column
    @XmlElement
    private Long receiverId;

    @Column
    @XmlElement
    private String message;

    @Column
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime messageDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }
}
