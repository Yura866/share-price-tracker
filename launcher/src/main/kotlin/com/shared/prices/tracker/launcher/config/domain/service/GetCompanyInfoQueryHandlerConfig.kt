package com.shared.prices.tracker.launcher.config.domain.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.shared.prices.tracker.domain.ports.incoming.LoadCompanyInfoUseCase
import com.shared.prices.tracker.domain.ports.outgoing.LoadCompanyInfoPort
import com.shared.prices.tracker.domain.service.cqs.handler.GetCompanyInfoQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class GetCompanyInfoQueryHandlerConfig {
    
    @Bean
    fun loadCompanyInfoUseCase(port: LoadCompanyInfoPort, clock: Clock): LoadCompanyInfoUseCase =
        GetCompanyInfoQueryHandler(port, clock)
    
    @Bean
    fun clock(): Clock = Clock.systemUTC()
    
    @Bean
    fun objectMapper(): ObjectMapper = JsonMapper.builder()
        .addModule(JavaTimeModule())
        .addModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        )
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .build()
}