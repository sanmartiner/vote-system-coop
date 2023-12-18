package com.coop.votingsystem.integration.mensageria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final RabbitTemplate rabbitTemplate;
    Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    public Consumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${voting.result.rabbitmq.queue}")
    public void consume(String message) {
        logger.info(message);
    }

}
