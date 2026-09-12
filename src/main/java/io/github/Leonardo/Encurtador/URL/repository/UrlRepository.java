package io.github.Leonardo.Encurtador.URL.repository;

import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    boolean existsUrlByShortCode(String shortCode);
    Url findUrlByShortCode(String shortCode);
    Url findUrlByOriginalUrl(String originalUrl);

    @Modifying
    @Query("""
            UPDATE Url u
            SET u.clickCount = u.clickCount + :quantidade
            WHERE u.shortCode = :shortCode
""")
    int incrementarCliques(
            @Param("shortCode") String shortCode,
            @Param("quantidade") Long quantidade
            );

}
