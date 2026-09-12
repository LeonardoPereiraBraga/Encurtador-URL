package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.dto.UrlEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedirectService {
    private final UrlService urlService;
    private final MessageProducer messageProducer;

    public String processarRedirect(String shortCode){
        String urlOriginal = urlService.puxarUrlOriginal(shortCode);
        messageProducer.processarCliques(new UrlEvent(shortCode));
        return urlOriginal;
    }
}
