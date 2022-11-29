package com.shared.prices.tracker.domain.service.mother

import com.shared.prices.tracker.domain.model.Company
import com.shared.prices.tracker.domain.model.SharePricesHolder

object CompanyMother {
    
    fun of(
        id: String = Casual.uuidStr(),
        externalId: String = Casual.uuidStr(),
        name: String = Casual.string(),
        sharePricesHolder: SharePricesHolder = SharePricesHolderMother.of()
    ) = Company(
        id = id,
        externalId = externalId,
        name = name,
        sharePricesHolder = sharePricesHolder
    )
}