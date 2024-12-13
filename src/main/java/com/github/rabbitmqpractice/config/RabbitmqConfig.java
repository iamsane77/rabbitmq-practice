package com.github.rabbitmqpractice.config;

import com.github.rabbitmqpractice.mq.RabbitmqReceiver;
import com.github.rabbitmqpractice.repository.MessageRepository;
import com.github.rabbitmqpractice.mq.RabbitmqSender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class RabbitmqConfig {
    @Bean
    public Queue queue() {
        return new Queue("example");
    }

    @Bean
    public RabbitmqReceiver receiver(MessageRepository messageRepository) {
        return new RabbitmqReceiver(messageRepository);
    }

    @Bean
    public RabbitmqSender sender(RabbitTemplate rabbitTemplate) {
        return new RabbitmqSender(rabbitTemplate);
    }
}
