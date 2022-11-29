package com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository

import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.document.SharePriceDoc
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant
import java.time.LocalDateTime

interface SharePriceRepository : MongoRepository<SharePriceDoc, String> {
    
    @Query("{ 'companyId': ?0, createdAt:{'\$gte': ?1 }}")
    fun findByCompanySince(
        @Param("companyId") companyId: String,
        @Param("since") since: Instant
    ): List<SharePriceDoc>
}