package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.dto.UrlPostResponse;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.github.Leonardo.Encurtador.URL.mapper.UrlMapper;
import io.github.Leonardo.Encurtador.URL.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    @Transactional
    public UrlPostResponse criarUrlEncurtada(UrlPostRequest webUrl){
        Url url = urlMapper.toUrl(webUrl);
        Url urlSalva = urlRepository.save(url);
        UrlPostResponse postResponse = urlMapper.toUrlPostResponse(urlSalva);
        return postResponse;
    }
    public String puxarUrlOriginal(String shortCode){
        Url urlEncontrada = urlRepository.findByShortCode(shortCode);
        return urlEncontrada.getOriginalUrl();
    }
}
