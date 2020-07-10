package com.xml.model;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailBinding {

    @Output("email-queue")
    MessageChannel sendMail();
}
