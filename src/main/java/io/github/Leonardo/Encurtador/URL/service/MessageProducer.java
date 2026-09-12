package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.config.RabbitMqConfig;
import io.github.Leonardo.Encurtador.URL.dto.UrlEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void processarCliques(UrlEvent urlEvent){
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTING_KEY, urlEvent);
    }


}
