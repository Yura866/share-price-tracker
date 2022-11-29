package com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.http

import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.MarketstackApiClient
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.dto.response.MarketstackApiResponse
import java.util.concurrent.CompletableFuture

class MarketstackApiHttpClient() : MarketstackApiClient {
    
    override fun getStockPrice(companyId: String): CompletableFuture<MarketstackApiResponse> {
        TODO("Not yet implemented")
    }
}