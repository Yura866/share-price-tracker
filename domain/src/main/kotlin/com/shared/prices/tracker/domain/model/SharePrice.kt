package com.shared.prices.tracker.domain.model

import java.math.BigDecimal
import java.time.Instant

data class SharePrice(
    val id: String? = null,
    val companyId: String,
    val price: BigDecimal,
    val createdAt: Instant,
)

