package redis.starter.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

public class RedisHashServiceImpl extends RedisHashService<String, String, Object> {
    public RedisHashServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public void put(String key, String hashKey, Object value) {
        System.out.println("putting");
        redisTemplate.opsForHash().put(key, hashKey, value);
        System.out.println("putted");
    }

    @Override
    public Optional<Object> get(String key, String hashKey) {
        System.out.println("getting");
        return Optional.ofNullable(redisTemplate.opsForHash().get(key, hashKey));
    }
}
