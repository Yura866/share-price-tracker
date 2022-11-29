package com.shared.prices.tracker.domain.service.cqs.handler

import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.service.mother.CompanyMother
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class GetCompaniesQueryHandlerShould {
    
    @InjectMockKs
    private lateinit var handler: GetCompaniesQueryHandler
    
    @MockK
    lateinit var port: CompanyRepositoryPort
    
    @AfterEach
    internal fun tearDown() {
        confirmVerified(
            port
        )
    }
    
    @Test
    fun `should successful call appropriate method`() {
        //given
        val companies = listOf(CompanyMother.of())
        every { port.findAllCompanies() }.returns(companies)
        //when
        handler.findAllCompanies()
        //then
        verify(exactly = 1) { port.findAllCompanies() }
    }
}