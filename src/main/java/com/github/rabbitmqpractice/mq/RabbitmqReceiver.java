package com.github.rabbitmqpractice.mq;

import com.github.rabbitmqpractice.domain.Message;
import com.github.rabbitmqpractice.repository.MessageRepository;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.transaction.annotation.Transactional;

@RabbitListener(queues = "example", ackMode = "MANUAL")
@Slf4j
@RequiredArgsConstructor
public class RabbitmqReceiver {
    private final MessageRepository messageRepository;

    @RabbitHandler
    @Transactional
    public void receive(String message,
                        Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws Exception {
        var messageEntity = Message.builder()
            .text(message)
            .build();

        messageRepository.saveAndFlush(messageEntity);

        channel.basicAck(deliveryTag, false);

        log.info(messageEntity.toString());
    }
}
