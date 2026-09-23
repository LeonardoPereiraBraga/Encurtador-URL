package io.github.Leonardo.Encurtador.URL.repository;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UrlRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UrlRepository urlRepository;

    @Test
    @DisplayName("Deve retonar Url com sucesso usando ShortCode")
    void findUrlByShortCodeSuccess() {
        UrlPostRequest dto = new UrlPostRequest("https://x.com/home");
        Url createdUrl = this.createUrl(dto);
        Url urlByShortCode = urlRepository.findUrlByShortCode("abc123");
        assertThat(urlByShortCode).isEqualTo(createdUrl);
    }

    private Url createUrl(UrlPostRequest dto){
        Url newUrl = new Url(null,"abc123",dto.getWebsiteUrl(), LocalDateTime.now(),0L,LocalDateTime.now().plusMonths(1));
        this.entityManager.persist(newUrl);
        return newUrl;
    }
}