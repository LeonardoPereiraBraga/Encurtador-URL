package io.github.Leonardo.Encurtador.URL.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final CacheProperties cacheProperties;

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(){
        var configuration = new RedisStandaloneConfiguration();
        configuration.setPassword(cacheProperties.getPassword());
        return configuration;
    }
}
