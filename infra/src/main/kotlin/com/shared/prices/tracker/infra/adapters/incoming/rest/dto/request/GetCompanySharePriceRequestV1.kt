package com.shared.prices.tracker.infra.adapters.incoming.rest.dto.request

import com.shared.prices.tracker.domain.service.cqs.DateUnit

data class GetCompanySharePriceRequestV1(
    val duration: Long = 1,
    val dateUnit: String = DateUnit.DAYS.name
)


