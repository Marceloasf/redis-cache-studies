package com.br.cache.repository

@FunctionalInterface
interface CacheRepository {

    fun set(key: String, value: String)
    fun get(key: String): String
}