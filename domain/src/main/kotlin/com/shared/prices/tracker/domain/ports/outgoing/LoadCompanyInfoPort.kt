package com.shared.prices.tracker.domain.ports.outgoing

import com.shared.prices.tracker.domain.model.Company
import java.time.Instant

interface LoadCompanyInfoPort {
    
    fun loadCompanyInfo(companyId: String, baselineDate: Instant): Company
}