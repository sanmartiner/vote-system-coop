package com.coop.votingsystem.integration.mensageria.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue votingResultQueue() {
        return new Queue("${voting.result.rabbitmq.queue}", false);
    }

    @Bean
    public FanoutExchange votingResultExchange() {
        return new FanoutExchange("${voting.result.rabbitmq.exchange}");
    }

    @Bean
    public Binding binding(Queue votingResultQueue, FanoutExchange votingResultExchange) {
        return BindingBuilder.bind(votingResultQueue).to(votingResultExchange);
    }
}
