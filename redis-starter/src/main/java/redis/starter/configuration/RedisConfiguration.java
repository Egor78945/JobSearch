package redis.starter.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.starter.configuration.environment.RedisEnvironment;
import redis.starter.service.HashCacheService;
import redis.starter.service.RedisHashServiceImpl;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(RedisEnvironment.class)
public class RedisConfiguration {
    @Bean
    @Primary
    public RedisConnectionFactory redisStarterConnectionFactory(RedisEnvironment properties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(properties.getPassword()));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    @Primary
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory, RedisEnvironment properties) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(properties.getLifetime()))
                .disableCachingNullValues();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisStarterTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @Primary
    public HashCacheService<String, String, Object>  hashCacheService(RedisTemplate<String, Object> redisTemplate) {
        return new RedisHashServiceImpl(redisTemplate);
    }
}
