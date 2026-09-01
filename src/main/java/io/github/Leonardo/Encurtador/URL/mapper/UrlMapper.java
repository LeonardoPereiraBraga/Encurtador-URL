package io.github.Leonardo.Encurtador.URL.mapper;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.dto.UrlPostResponse;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.github.Leonardo.Encurtador.URL.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlMapper {
    private final UrlRepository urlRepository;

    public Url toUrl(UrlPostRequest urlPostRequest){
        String shortCode = generateShortCode();
        LocalDateTime createdAt = LocalDateTime.now();
        Url urlMappeada = new Url(null, shortCode, urlPostRequest.getWebsiteUrl(), createdAt);
        return urlMappeada;
    }

    public UrlPostResponse toUrlPostResponse (Url url){
        UrlPostResponse urlPostResponse = new UrlPostResponse(url.getId(), url.getShortCode(), url.getOriginalUrl());
        return urlPostResponse;
    }

    public String generateShortCode(){
        String shortCode;
        do {
            shortCode = UUID.randomUUID().toString().substring(0,6);
        } while (urlRepository.existsUrlByShortCode(shortCode));
        return  shortCode;
    }
}
