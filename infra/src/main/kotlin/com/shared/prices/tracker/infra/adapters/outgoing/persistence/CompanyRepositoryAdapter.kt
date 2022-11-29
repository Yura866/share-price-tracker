package com.shared.prices.tracker.infra.adapters.outgoing.persistence

import com.shared.prices.tracker.domain.exception.ModelNotFoundException
import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.model.SharePrice
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.ports.outgoing.LoadCompanyInfoPort
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.CompanyRepository
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.SharePriceRepository
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document.CompanyDoc
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.mapper.toModel
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.mapper.toSharePriceDoc
import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger { }

class CompanyRepositoryAdapter(
    private val companyRepository: CompanyRepository,
    private val sharePriceRepository: SharePriceRepository
) : CompanyRepositoryPort, LoadCompanyInfoPort {
    
    // TODO Make this function pageable
    override fun findAllCompanies(): List<Company> {
        return companyRepository
            .findAll()
            .map { it.toModel(listOf()) }
    }
    
    override fun loadCompanyInfo(companyId: String, baselineDate: Instant): Company {
        val company = companyRepository
            .findByIdOrNull(companyId)
            ?: throw ModelNotFoundException(Company::class, companyId)
        val sharePrices = sharePriceRepository.findByCompanySince(company.id, baselineDate)
        
        return company.toModel(sharePrices)
    }
    
    override fun addSharePrice(sharePrice: SharePrice) {
        sharePriceRepository.insert(
            sharePrice.toSharePriceDoc()
        )
    }
    
    @PostConstruct
    fun saveCompaniesMock() {
        companyRepository.saveAll(
            listOf(
                CompanyDoc(
                    id = UUID.nameUUIDFromBytes("Apple-1".toByteArray()).toString(),
                    externalId = UUID.nameUUIDFromBytes("Apple".toByteArray()).toString(),
                    name = "Apple",
                ),
                CompanyDoc(
                    id = UUID.nameUUIDFromBytes("Amazon-2".toByteArray()).toString(),
                    externalId = UUID.nameUUIDFromBytes("Amazon".toByteArray()).toString(),
                    name = "Amazon",
                ),
                CompanyDoc(
                    id = UUID.nameUUIDFromBytes("Tesla-3".toByteArray()).toString(),
                    externalId = UUID.nameUUIDFromBytes("Tesla".toByteArray()).toString(),
                    name = "Tesla",
                )
            )
        )
    }
}