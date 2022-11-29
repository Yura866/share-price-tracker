package com.shared.prices.tracker.launcher.config.domain.service

import com.shared.prices.tracker.domain.ports.incoming.RetrieveCompanyUseCase
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.service.cqs.handler.GetCompaniesQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GetCompaniesQueryHandlerConfig {
    
    @Bean
    fun retrieveCompanyUseCase(port: CompanyRepositoryPort): RetrieveCompanyUseCase {
        return GetCompaniesQueryHandler(port)
    }
}