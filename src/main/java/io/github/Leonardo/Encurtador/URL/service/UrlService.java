package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.dto.UrlPostResponse;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.github.Leonardo.Encurtador.URL.mapper.UrlMapper;
import io.github.Leonardo.Encurtador.URL.repository.UrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    @Transactional
    @CacheEvict(cacheNames = "urls", allEntries = true)
    public UrlPostResponse criarUrlEncurtada(UrlPostRequest webUrl){
        Url url = urlMapper.toUrl(webUrl);
        Url urlSalva = urlRepository.save(url);
        UrlPostResponse postResponse = urlMapper.toUrlPostResponse(urlSalva);
        return postResponse;
    }

    @Cacheable(cacheNames = "urls", key ="#root.method.name")
    public String puxarUrlOriginal(String shortCode){
        System.out.println("Nao foi pego no Cache");
        Url urlEncontrada = urlRepository.findByShortCode(shortCode);
        return urlEncontrada.getOriginalUrl();
    }
}
