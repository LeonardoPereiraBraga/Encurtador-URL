package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.config.RabbitMqConfig;
import io.github.Leonardo.Encurtador.URL.dto.UrlEvent;
import io.github.Leonardo.Encurtador.URL.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageConsumer {
    private final UrlRepository urlRepository;

    @RabbitListener(queues = RabbitMqConfig.QUEUE, containerFactory = "batchFactory")
    @Transactional
    public void receiveMessage(List<UrlEvent> urlEvent){
        System.out.println("LOTE RECEBIDO: " + urlEvent.size());
        Map<String, Long> clicks = urlEvent.stream().collect(Collectors.groupingBy(UrlEvent::getShortCode, Collectors.counting()));
        clicks.forEach((shortCode,count) -> {
            urlRepository.incrementarCliques(shortCode,count);
        });

    }
}
