package com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Document("share_prices")
data class SharePriceDoc(
    @Id
    val id: String = UUID.randomUUID().toString(),
    @field:Indexed
    val companyId: String,
    val price: BigDecimal,
    val createdAt: Instant = Instant.now(),
)