package com.shared.prices.tracker.domain.model

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime

data class StockPriceApiDataResponse(
    val companyId: String,
    val date: Instant,
    val actualSharePrice: BigDecimal
)
