package com.shared.prices.tracker.domain.service.processor

import com.shared.prices.tracker.domain.exception.MarketstackApiException
import com.shared.prices.tracker.domain.model.SharePrice
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.ports.outgoing.MarketstackApiClientPort
import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class CompanyPullProcessor(
    private val apiClientPort: MarketstackApiClientPort,
    private val repositoryPort: CompanyRepositoryPort,
) {
    
    fun doProcess() {
        try {
            log.debug { "Trying to pull latest companies share prices" }
            val companies = repositoryPort.findAllCompanies()
            
            companies.forEach {
                val apiResponse = apiClientPort.pullLatestSharePrice(it.externalId)
                
                repositoryPort.addSharePrice(
                    SharePrice(
                        companyId = it.id,
                        createdAt = apiResponse.date,
                        price = apiResponse.actualSharePrice,
                    )
                )
            }
        } catch (e: MarketstackApiException) {
            log.error(e) { "Failed processing external companies data: ${e.message} " }
            //TODO handle errors properly
        } catch (e: Exception) {
            log.error(e) { "An unexpected exception occurred trying to upgrade company share prices " }
            //TODO handle errors properly
        }
    }
}
