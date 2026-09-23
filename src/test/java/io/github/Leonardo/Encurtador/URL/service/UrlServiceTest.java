package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.dto.UrlPostResponse;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.github.Leonardo.Encurtador.URL.exception.UrlExpiradaException;
import io.github.Leonardo.Encurtador.URL.mapper.UrlMapper;
import io.github.Leonardo.Encurtador.URL.repository.UrlRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @InjectMocks
    private UrlService urlService;

    @Mock
    UrlMapper urlMapper;

    @Mock
    UrlRepository urlRepository;

    @Test
    @DisplayName("Deve criar Url Encurtada com sucesso")
    void criarUrlEncurtadaSucesso() {
        UrlPostRequest request = new UrlPostRequest("https://x.com/home");
        Url mappedUrl = new Url(1L, "abc10",request.getWebsiteUrl(), LocalDateTime.now(),0L,LocalDateTime.now().plusMonths(1));
        UrlPostResponse postResponse = new UrlPostResponse(mappedUrl.getId(),mappedUrl.getShortCode(),mappedUrl.getOriginalUrl());
        when(urlMapper.toUrl(request)).thenReturn(mappedUrl);
        when(urlRepository.save(mappedUrl)).thenReturn(mappedUrl);
        when(urlMapper.toUrlPostResponse(mappedUrl)).thenReturn(postResponse);
        assertThat(urlService.criarUrlEncurtada(request)).isEqualTo(postResponse);
        //Verificar se o repository foi chamado 1 vez
        verify(urlRepository, times(1)).save(mappedUrl);
    }
    @Test
    @DisplayName("Deve retornar uma excecao quando a url estiver expirada")
    void puxarUrlOriginalRetornaExcecaoQuandoExpirado(){
        Url urlToFound = new Url(1L, "abc12", "https://x.com/home", LocalDateTime.now(),0L,LocalDateTime.now().minusMonths(1));
        when(urlRepository.findUrlByShortCode("abc12")).thenReturn(urlToFound);
        UrlExpiradaException expiradaException = assertThrows(UrlExpiradaException.class,
                () -> urlService.puxarUrlOriginal("abc12"));
        System.out.println(expiradaException.getMessage());
    }
}