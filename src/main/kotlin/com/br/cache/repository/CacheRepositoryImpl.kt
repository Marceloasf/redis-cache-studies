package com.br.cache.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class CacheRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, String>
) : CacheRepository {

    override fun set(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(30))
    }

    override fun get(key: String): String {
        return redisTemplate.opsForValue().get(key) ?: error("Value not found for key: $key")
    }
}