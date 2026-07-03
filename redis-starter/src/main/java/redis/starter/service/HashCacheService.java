package redis.starter.service;

import java.util.Optional;

public interface HashCacheService<K, HK, V> {
    void put(K key, HK hashKey, V value);
    Optional<V> get(K key, HK hashKey);
}
