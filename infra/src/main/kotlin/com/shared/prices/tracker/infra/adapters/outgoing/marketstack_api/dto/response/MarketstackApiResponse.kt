package com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.dto.response

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime

data class MarketstackApiResponse(
    val data: List<MarketstackApiData>
)

data class MarketstackApiData(
    val date: Instant,
    val companyId: String,
    val price: BigDecimal
)
