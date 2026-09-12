package io.github.Leonardo.Encurtador.URL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "click_count")
    private Long clickCount;
}
