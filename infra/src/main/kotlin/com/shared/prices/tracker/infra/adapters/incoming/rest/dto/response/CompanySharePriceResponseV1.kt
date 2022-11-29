package com.shared.prices.tracker.infra.adapters.incoming.rest.dto.response

import com.shared.prices.tracker.common.dto.SharePriceV1

data class CompanySharePriceResponseV1(
    val sharePrices: List<SharePriceV1> = listOf()
)
