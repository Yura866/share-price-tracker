package com.shared.prices.tracker.domain.service.cqs.handler

import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.ports.incoming.RetrieveCompanyUseCase
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort

class GetCompaniesQueryHandler(
    private val port: CompanyRepositoryPort
) : RetrieveCompanyUseCase {
    
    override fun findAllCompanies(): List<Company> {
        return port.findAllCompanies()
    }
}