package com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository

import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document.CompanyDoc
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<CompanyDoc, String> {
}