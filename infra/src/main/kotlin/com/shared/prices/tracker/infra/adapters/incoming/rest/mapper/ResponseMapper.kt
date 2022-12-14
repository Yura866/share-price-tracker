package com.shared.prices.tracker.infra.adapters.incoming.rest.mapper

import com.shared.prices.tracker.common.dto.SharePriceV1
import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.model.SharePrice
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.response.CompanyDataV1
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.response.CompanyResponseV1
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.response.CompanySharePriceResponseV1

fun List<Company>.toResponse() = CompanyResponseV1(
    companies = this.map {
        CompanyDataV1(
            id = it.id,
            name = it.name,
        )
    }
)

fun Company.toCompanySharePriseResponse() = CompanySharePriceResponseV1(
    sharePrices = this.sharePricesHolder.sharePrices.map { it.toDto() }
)

fun SharePrice.toDto() = SharePriceV1(
    id = id,
    companyId = companyId,
    price = price,
    createdAt = createdAt
)