package com.shared.prices.tracker.infra.adapters.incoming.rest.dto.response

data class CompanyResponseV1(
    val companyData: CompanyDataV1,
)

data class CompanyDataV1(
    val id: String,
    val name: String,
)
