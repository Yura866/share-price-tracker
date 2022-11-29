package com.shared.prices.tracker.launcher.config.domain.service

import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.ports.outgoing.MarketstackApiClientPort
import com.shared.prices.tracker.domain.service.processor.CompanyPullProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CompanyPullProcessorConfig {
    
    @Bean
    fun companyPullProcessor(
        apiClientPort: MarketstackApiClientPort,
        repositoryPort: CompanyRepositoryPort,
    ): CompanyPullProcessor {
        return CompanyPullProcessor(apiClientPort, repositoryPort)
    }
}