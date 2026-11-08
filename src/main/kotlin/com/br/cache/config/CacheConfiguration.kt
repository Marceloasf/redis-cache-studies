package com.br.cache.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import java.time.Duration

private val logger = KotlinLogging.logger {}

@Configuration
class CacheConfiguration {

    /**
     * Standalone Lettuce ConnectionFactory
     */
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {

        val clientConfig = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofSeconds(2))
            .shutdownTimeout(Duration.ZERO)
            .build()

        val hostname = "localhost"
        val port = 6380

        logger.info { "Setting up RedisConnectionFactory with Lettuce client for hostname: $hostname and port: $port" }

        return LettuceConnectionFactory(
            RedisStandaloneConfiguration("localhost", 6380),
            clientConfig
        )
    }

//    OPTIONAL
//    /**
//     * Custom RedisTemplate Bean using our custom ConnectionFactory
//     */
//    @Bean
//    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, String> {
//
//        val redisTemplate = RedisTemplate<String, String>()
//        redisTemplate.connectionFactory = redisConnectionFactory
//
//        logger.info { "Setting up custom RedisTemplate for custom RedisConnectionFactory" }
//
//        return redisTemplate
//    }

//    /**
//     * Write to Master, Read from Replica - ConnectionFactory
//     */
//    @Bean
//    fun redisConnectionFactory(): LettuceConnectionFactory {
//
//        val clientConfig = LettuceClientConfiguration.builder()
//            .useSsl().and()
//            .readFrom(REPLICA_PREFERRED)
//            .commandTimeout(Duration.ofSeconds(2))
//            .shutdownTimeout(Duration.ZERO)
//            .build()
//
//        // Can use RedisStaticMasterReplicaConfiguration instead - Configuration class used for setting up
//        // RedisConnection via RedisConnectionFactory using the provided Master / Replica configuration to
//        // nodes know to not change address. Useful when connecting to AWS ElastiCache with Read Replicas.
//
//        return LettuceConnectionFactory(
//            RedisStandaloneConfiguration("server", 6379),
//            clientConfig)
//    }
}