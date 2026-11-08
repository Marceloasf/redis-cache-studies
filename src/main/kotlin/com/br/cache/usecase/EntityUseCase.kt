package com.br.cache.usecase

import com.br.cache.repository.CacheRepository
import com.br.cache.utils.isValidURL
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

/**
 * "Entity" is anything that you want to store in the cache.
 */
@Component
class EntityUseCase(
    private val repository: CacheRepository
) {

    fun setEntityValueByKey(key: String, value: String) {
        if (isValidURL(value).not()) {
            error("'${value}' is not a valid URL")
        }

        repository.set(key, value)
        logger.info { "Stored key=$key and value=$value in cache" }
    }

    fun getEntityByKey(key: String): String {
        val value = repository.get(key)
        logger.info { "Found stored value=$value for key=$key in cache" }
        return value
    }
}