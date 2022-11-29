package com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api

import com.shared.prices.tracker.domain.exception.MarketstackApiException
import com.shared.prices.tracker.domain.model.StockPriceApiDataResponse
import com.shared.prices.tracker.domain.ports.outgoing.MarketstackApiClientPort
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.MarketstackApiClient
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class MarketstackApiClientAdapter(
    private val apiClient: MarketstackApiClient
) : MarketstackApiClientPort {
    
    override fun pullLatestSharePrice(companyId: String): StockPriceApiDataResponse {
        val apiResponse = retrieveStockPrice(companyId)
        return apiResponse.data.map {
            StockPriceApiDataResponse(
                companyId = it.companyId,
                date = it.date,
                actualSharePrice = it.price
            )
        }.first()
    }
    
    private fun retrieveStockPrice(companyId: String) = try {
        apiClient.getStockPrice(companyId).join()
    } catch (e: Exception) {
        // TODO properly handle exceptions, map and throw custom one
        log.error { "Failed to retrieve stock prices data from Marketstack Api" }
        throw MarketstackApiException("Failed to retrieve stock prices data from Marketstack Api.  ${e.message}")
    }
}