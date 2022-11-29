package com.shared.prices.tracker.launcher.config.infra.adapters

import com.shared.prices.tracker.domain.service.processor.CompanyPullProcessor
import com.shared.prices.tracker.infra.schedule.CompanyPullingExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class CompanyPullingExecutorConfig {
    
    @Bean fun companyPullingExecutor(processor: CompanyPullProcessor) = CompanyPullingExecutor(processor)
}