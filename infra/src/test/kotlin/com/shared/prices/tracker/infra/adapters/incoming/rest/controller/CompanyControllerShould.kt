package com.shared.prices.tracker.infra.adapters.incoming.rest.controller

import com.shared.prices.tracker.domain.ports.incoming.RetrieveCompanyUseCase
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.constant.Endpoint
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

@ExtendWith(MockKExtension::class)
internal class CompanyControllerShould {
    
    @InjectMockKs
    private lateinit var controller: CompanyController
    
    @RelaxedMockK
    private lateinit var mockMvc: MockMvc
    
    @MockK
    private lateinit var useCase: RetrieveCompanyUseCase
    
    @BeforeEach
    internal fun setUp() {
        MockMvcBuilders.standaloneSetup(controller).build()
    }
    
    @Test
    fun `should successfully call get companies endpoint`() {
        //given when then
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.GET_COMPANIES))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }
}