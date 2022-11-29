package com.shared.prices.tracker.domain.service.cqs.handler

import com.shared.prices.tracker.domain.ports.outgoing.LoadCompanyInfoPort
import com.shared.prices.tracker.domain.service.cqs.DateUnit
import com.shared.prices.tracker.domain.service.cqs.GetCompanyInfoQuery
import com.shared.prices.tracker.domain.service.mother.Casual
import com.shared.prices.tracker.domain.service.mother.CompanyMother
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit

@ExtendWith(MockKExtension::class)
internal class GetCompanyInfoQueryHandlerShould {
    
    @InjectMockKs
    private lateinit var handler: GetCompanyInfoQueryHandler
    
    @MockK
    lateinit var port: LoadCompanyInfoPort
    
    @MockK
    private lateinit var clock: Clock
    private val givenDate = Instant.now()
    
    @BeforeEach
    internal fun setUp() {
        every { clock.instant() } returns givenDate
    }
    
    @AfterEach
    fun tearDown() {
        confirmVerified(
            port
        )
    }
    
    @Test
    fun loadCompanyInfo() {
        // given
        val giveQuery = GetCompanyInfoQuery(
            companyId = Casual.uuidStr(),
            duration = 1,
            DateUnit.DAYS
        )
        val startedLineDate = givenDate.minus(giveQuery.duration, ChronoUnit.valueOf(giveQuery.dateUnit.name))
        val givenCompany = CompanyMother.of()
        every { port.loadCompanyInfo(any(), any()) }.returns(givenCompany)
        //when
        handler.loadCompanyInfo(giveQuery)
        //then
        verify(exactly = 1) { port.loadCompanyInfo(giveQuery.companyId, startedLineDate) }
    }
}