package com.shared.prices.tracker.domain.service.mother

import com.shared.prices.tracker.domain.model.SharePrice
import com.shared.prices.tracker.domain.model.SharePricesHolder

object SharePricesHolderMother {
    
    fun of(sharePrices: List<SharePrice> = listOf()) = SharePricesHolder(
        sharePrices = sharePrices
    )
}