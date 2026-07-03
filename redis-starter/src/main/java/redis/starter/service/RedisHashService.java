package redis.starter.service;

import org.springframework.data.redis.core.RedisTemplate;

public abstract class RedisHashService<K, HK, V> implements HashCacheService<K, HK, V> {
    protected final RedisTemplate<K, V> redisTemplate;

    public RedisHashService(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
