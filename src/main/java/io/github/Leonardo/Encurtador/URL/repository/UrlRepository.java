package io.github.Leonardo.Encurtador.URL.repository;

import io.github.Leonardo.Encurtador.URL.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    boolean existsUrlByShortCode(String shortCode);
    Url findByShortCode(String shortCode);
}
