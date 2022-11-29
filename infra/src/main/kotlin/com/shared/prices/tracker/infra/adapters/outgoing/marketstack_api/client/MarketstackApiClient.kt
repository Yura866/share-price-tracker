package com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client

import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.dto.response.MarketstackApiResponse
import java.util.concurrent.CompletableFuture

interface MarketstackApiClient {
    
    fun getStockPrice(companyId: String): CompletableFuture<MarketstackApiResponse>
    
}