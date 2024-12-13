package com.github.rabbitmqpractice.mq;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        rabbitTemplate.convertAndSend("example", "Example message " + UUID.randomUUID());
    }
}
