package com.shared.prices.tracker.domain.ports.outgoing

import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.model.SharePrice

interface CompanyRepositoryPort {
    
    fun findAllCompanies(): List<Company>
    
    fun addSharePrice(sharePrice: SharePrice)
}
