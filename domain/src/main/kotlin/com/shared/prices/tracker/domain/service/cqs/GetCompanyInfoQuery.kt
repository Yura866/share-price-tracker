package com.shared.prices.tracker.domain.service.cqs

data class GetCompanyInfoQuery(
    val companyId: String,
    val duration: Long,
    val dateUnit: DateUnit
)

enum class DateUnit {
    HOURS, DAYS, WEEKS
}
