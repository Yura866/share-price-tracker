package com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.fake

import com.shared.prices.tracker.domain.exception.MarketstackApiException
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.MarketstackApiClient
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.dto.response.MarketstackApiData
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.dto.response.MarketstackApiResponse
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Instant
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.random.Random

class MarketstackApiFakeClient : MarketstackApiClient {
    
    override fun getStockPrice(companyId: String): CompletableFuture<MarketstackApiResponse> {
        return CompletableFuture.completedFuture(
            MarketstackApiResponse(
                listOf(getByCompanyId(companyId))
            )
        )
    }
    
    private fun getByCompanyId(companyId: String): MarketstackApiData {
        return mapOf(
            UUID.nameUUIDFromBytes("Tesla".toByteArray()).toString() to MarketstackApiData(
                date = Instant.now(),
                companyId = UUID.nameUUIDFromBytes("Tesla".toByteArray()).toString(),
                price = BigDecimal.valueOf(Random.nextDouble()).setScale(3, RoundingMode.HALF_EVEN)
            ),
            UUID.nameUUIDFromBytes("Apple".toByteArray()).toString() to
                    MarketstackApiData(
                        date = Instant.now(),
                        companyId = UUID.nameUUIDFromBytes("Apple".toByteArray()).toString(),
                        price = BigDecimal.valueOf(Random.nextDouble()).setScale(3, RoundingMode.HALF_EVEN)
                    ),
            UUID.nameUUIDFromBytes("Amazon".toByteArray()).toString() to
                    MarketstackApiData(
                        date = Instant.now(),
                        companyId = UUID.nameUUIDFromBytes("Amazon".toByteArray()).toString(),
                        price = BigDecimal.valueOf(Random.nextDouble()).setScale(3, RoundingMode.HALF_EVEN)
                    ),
        )[companyId] ?: throw MarketstackApiException("Share price is not found by company id: $companyId")
    }
}