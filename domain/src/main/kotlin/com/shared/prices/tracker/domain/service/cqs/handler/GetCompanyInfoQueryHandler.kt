package com.shared.prices.tracker.domain.service.cqs.handler

import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.ports.incoming.LoadCompanyInfoUseCase
import com.shared.prices.tracker.domain.ports.outgoing.LoadCompanyInfoPort
import com.shared.prices.tracker.domain.service.cqs.GetCompanyInfoQuery
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit

class GetCompanyInfoQueryHandler(
    private val port: LoadCompanyInfoPort,
    private val clock: Clock,
) : LoadCompanyInfoUseCase {
    
    override fun loadCompanyInfo(query: GetCompanyInfoQuery): Company {
        val baselineDate = Instant.now(clock)
            .minus(
                query.duration,
                ChronoUnit.valueOf(query.dateUnit.name)
            )
        
        return port.loadCompanyInfo(query.companyId, baselineDate)
    }
}