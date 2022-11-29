package com.shared.prices.tracker.domain.ports.incoming

import com.shared.prices.tracker.domain.model.Company

interface RetrieveCompanyUseCase {
    
    fun findAllCompanies(): List<Company>
}