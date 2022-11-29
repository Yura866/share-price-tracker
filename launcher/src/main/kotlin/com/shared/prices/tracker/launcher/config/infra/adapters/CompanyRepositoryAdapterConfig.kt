package com.shared.prices.tracker.launcher.config.infra.adapters

import com.shared.prices.tracker.infra.adapters.outgoing.persistence.CompanyRepositoryAdapter
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.CompanyRepository
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.SharePriceRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.shared.prices.tracker.infra.adapters.outgoing"])
class CompanyRepositoryAdapterConfig {
    
    @Bean
    @ConditionalOnProperty(
        value = ["company.mongodb.transactions.enabled"],
        havingValue = "true",
        matchIfMissing = true
    )
    fun transactionManager(dbFactory: MongoDatabaseFactory) = MongoTransactionManager(dbFactory)
    
    @Bean
    fun companyRepositoryAdapter(
        companyRepository: CompanyRepository,
        sharePriceRepository: SharePriceRepository
    ) = CompanyRepositoryAdapter(companyRepository, sharePriceRepository)
}