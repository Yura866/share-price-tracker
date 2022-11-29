package com.shared.prices.tracker.domain.service.processor

import com.shared.prices.tracker.domain.model.SharePrice
import com.shared.prices.tracker.domain.model.StockPriceApiDataResponse
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.ports.outgoing.MarketstackApiClientPort
import com.shared.prices.tracker.domain.service.mother.Casual
import com.shared.prices.tracker.domain.service.mother.CompanyMother
import io.kotest.matchers.shouldBe
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.Instant

@ExtendWith(MockKExtension::class)
internal class CompanyPullProcessorShould {
    
    @InjectMockKs
    private lateinit var processor: CompanyPullProcessor
    
    @MockK
    private lateinit var apiClientPort: MarketstackApiClientPort
    
    @MockK
    private lateinit var repositoryPort: CompanyRepositoryPort
    private val sharePriceSlot = slot<SharePrice>()
    
    @AfterEach
    internal fun tearDown() {
        confirmVerified(
            apiClientPort,
            repositoryPort
        )
    }
    
    @Test
    fun `successful execute pulling process and process create an updated company share price`() {
        //given
        val givenExternalCompanyId = Casual.uuidStr()
        val givenCompanyId = Casual.uuidStr()
        val givenCompany = CompanyMother.of(id = givenCompanyId, externalId = givenExternalCompanyId)
        val giveCompanies = listOf(givenCompany)
        val givenDate = Instant.now()
        val givenAPiResponse = StockPriceApiDataResponse(
            companyId = givenExternalCompanyId,
            date = givenDate,
            actualSharePrice = BigDecimal.TEN
        )
        
        every { repositoryPort.findAllCompanies() }.returns(giveCompanies)
        every { apiClientPort.pullLatestSharePrice(givenExternalCompanyId) }.returns(givenAPiResponse)
        every { repositoryPort.addSharePrice(any()) } just Runs
        //when
        processor.doProcess()
        //then
        verify(exactly = 1) {
            repositoryPort.findAllCompanies()
            apiClientPort.pullLatestSharePrice(givenExternalCompanyId)
            repositoryPort.addSharePrice(capture(sharePriceSlot))
        }
        
        sharePriceSlot.captured shouldBe SharePrice(
            companyId = givenCompanyId,
            price = BigDecimal.TEN,
            createdAt = givenDate
        )
    }
}