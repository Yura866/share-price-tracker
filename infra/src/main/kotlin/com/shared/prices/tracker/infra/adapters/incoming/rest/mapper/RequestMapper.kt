package com.shared.prices.tracker.infra.adapters.incoming.rest.mapper

import com.shared.prices.tracker.domain.service.cqs.DateUnit
import com.shared.prices.tracker.domain.service.cqs.GetCompanyInfoQuery
import com.shared.prices.tracker.domain.exception.BadRequestCode
import com.shared.prices.tracker.domain.exception.BadRequestException
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.request.GetCompanySharePriceRequestV1

fun GetCompanySharePriceRequestV1.toQuery(companyId: String) = GetCompanyInfoQuery(
    companyId = companyId,
    duration = duration,
    dateUnit = try {
        DateUnit.valueOf(dateUnit)
    } catch (e: IllegalArgumentException) {
        throw BadRequestException(
            code = BadRequestCode.UNKNOWN_TIME_UNIT,
            message = "Unknown time unit type. Supported time units: ${DateUnit.values().joinToString()}"
        )
    }
)