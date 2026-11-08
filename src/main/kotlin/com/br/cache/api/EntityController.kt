package com.br.cache.api

import com.br.cache.usecase.EntityUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/entities")
class EntityController(
    private val useCase: EntityUseCase
) {

    @PutMapping("/{key}/values")
    fun setEntityValueByKey(
        @PathVariable("key") key: String,
        @RequestBody value: String
    ): ResponseEntity<Void> {

        useCase.setEntityValueByKey(key, value)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{key}")
    fun getEntityValueByKey(@PathVariable key: String): ResponseEntity<String> {

        return ResponseEntity.ok(useCase.getEntityByKey(key))
    }
}