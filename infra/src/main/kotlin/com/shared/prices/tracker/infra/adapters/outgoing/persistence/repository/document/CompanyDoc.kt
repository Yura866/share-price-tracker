package com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("companies")
data class CompanyDoc(
    @Id
    val id: String,
    val externalId: String,
    val name: String,
)
