package io.github.Leonardo.Encurtador.URL.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "spring.data.redis")
public class CacheProperties {
    private Integer port;
    private String host;
    private String password;
}
