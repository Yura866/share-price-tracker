package com.shared.prices.tracker.domain.ports.incoming

import com.shared.prices.tracker.domain.service.cqs.GetCompanyInfoQuery
import com.shared.prices.tracker.domain.model.Company

interface LoadCompanyInfoUseCase {
    
    fun loadCompanyInfo(query: GetCompanyInfoQuery): Company
}