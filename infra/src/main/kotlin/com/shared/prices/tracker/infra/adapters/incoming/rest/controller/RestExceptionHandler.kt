package com.shared.prices.tracker.infra.adapters.incoming.rest.controller

import com.shared.prices.tracker.common.dto.ErrorCode
import com.shared.prices.tracker.common.dto.ErrorV1
import com.shared.prices.tracker.domain.exception.BadRequestException
import com.shared.prices.tracker.domain.exception.ModelNotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class RestExceptionHandler {
    
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(exception: BadRequestException): List<ErrorV1> {
        log.warn { exception.message }
        return exception.errors.map { ErrorV1(it.key, it.value) }
    }
    
    @ExceptionHandler(ModelNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handle(exception: ModelNotFoundException): ErrorV1 {
        return ErrorV1(ErrorCode.NOT_FOUND, exception.message ?: "Unknown message")
    }
}