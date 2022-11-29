package com.shared.prices.tracker.domain.model

data class Company(
    val id: String,
    val externalId: String,
    val name: String,
    val sharePricesHolder: SharePricesHolder
)