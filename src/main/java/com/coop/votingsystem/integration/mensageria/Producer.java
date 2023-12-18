package com.coop.votingsystem.integration.mensageria;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    @Value("${voting.result.rabbitmq.exchange}")
    private String rabbitmqExchange;

    @Value("${voting.result.rabbitmq.routing-key}")
    private String rabbitmqRoutingKey;

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public Producer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(String message) {
        amqpTemplate.convertAndSend(rabbitmqExchange, rabbitmqRoutingKey, message);
    }
}
