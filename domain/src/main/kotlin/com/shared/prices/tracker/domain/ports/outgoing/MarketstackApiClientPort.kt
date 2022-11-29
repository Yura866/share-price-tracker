package com.shared.prices.tracker.domain.ports.outgoing

import com.shared.prices.tracker.domain.model.StockPriceApiDataResponse

interface MarketstackApiClientPort {
    
    fun pullLatestSharePrice(companyId: String): StockPriceApiDataResponse
}