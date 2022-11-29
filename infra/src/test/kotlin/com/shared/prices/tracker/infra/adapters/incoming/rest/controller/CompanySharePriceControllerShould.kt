package com.shared.prices.tracker.infra.adapters.incoming.rest.controller

import com.shared.prices.tracker.domain.ports.incoming.LoadCompanyInfoUseCase
import com.shared.prices.tracker.domain.service.cqs.DateUnit
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.constant.Endpoint
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.request.GetCompanySharePriceRequestV1
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.UUID

@ExtendWith(MockKExtension::class)
internal class CompanySharePriceControllerShould {
    
    @InjectMockKs
    private lateinit var controller: CompanySharePriceController
    
    @RelaxedMockK
    private lateinit var mockMvc: MockMvc
    
    @MockK
    private lateinit var useCase: LoadCompanyInfoUseCase
    
    @BeforeEach
    internal fun setUp() {
        MockMvcBuilders.standaloneSetup(controller).build()
    }
    
    @Test
    fun `should successfully call get company info endpoint`() {
        //given
        val companyId = UUID.randomUUID().toStr()
        val request = GetCompanySharePriceRequestV1(
            duration = 1,
            dateUnit = DateUnit.DAYS.name
        )
    
        //controller.getSharePrices(companyId, request)
        //given when then
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.GET_SHARE_PRICES, companyId).content(request.toStr()))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }
}