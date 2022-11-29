package com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.mapper

import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.model.SharePrice
import com.shared.prices.tracker.domain.model.SharePricesHolder
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document.CompanyDoc
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document.SharePriceDoc

fun SharePriceDoc.toModel() = SharePrice(
    id = id,
    companyId = companyId,
    price = price,
    createdAt = createdAt
)

fun SharePrice.toSharePriceDoc() = SharePriceDoc(
    companyId = companyId,
    price = price,
    createdAt = createdAt
)

fun CompanyDoc.toModel(sharePrices: List<SharePriceDoc>) = Company(
    id = id,
    externalId = externalId,
    name = name,
    sharePricesHolder = SharePricesHolder(
        sharePrices = sharePrices.map { it.toModel() }
    )
)