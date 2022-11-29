package com.shared.prices.tracker.common.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.Instant

data class SharePriceV1(
    val id: String? = null,
    val companyId: String,
    val price: BigDecimal,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    val createdAt: Instant,
)
