package com.xml.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    static final String topicExchangeName = "advertisement-codebook-exchange";

    static final String queueName = "advertisement-codebook";

    static final String routingKey = "advertisement-codebook-key";

    public static String exchangeNameForAdvertisement = "codebook-advertisement-exchange";

    public static String queueNameForAdvertisement = "codebook-advertisement";

    public static String routingKeyForAdvertisement = "codebook-advertisement-key";


    // email
    public static final String emailQueue = "email-queue";
    public static final String emailExchange = "email-exchange";
    public static final String emailKey = "email-key";

    @Bean
    Queue queue() {
        return new Queue(queueNameForAdvertisement, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeNameForAdvertisement);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyForAdvertisement);
    }

    // email
    @Bean
    Queue emailQueue() { return new Queue(emailQueue, false); }

    @Bean
    TopicExchange emailExchange() {
        return new TopicExchange(emailExchange);
    }

    @Bean
    Binding emailBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(emailKey);
    }

    @Bean
    Queue queueName() {
        return new Queue(queueName, false);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
