package com.shared.prices.tracker.launcher

import com.fasterxml.jackson.databind.ObjectMapper
import com.shared.prices.tracker.domain.ports.incoming.RetrieveCompanyUseCase
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.constant.Endpoint
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.CompanyRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
internal class ATCompanyControllerShould {
    
    private lateinit var mockMvc: MockMvc
    
    @Autowired
    private lateinit var context: WebApplicationContext
    
    @Autowired
    private lateinit var handler: RetrieveCompanyUseCase
    
    @Autowired
    private lateinit var port: CompanyRepositoryPort
    
    @Autowired
    private lateinit var companyRepository: CompanyRepository
    
    @Autowired
    private lateinit var mapper: ObjectMapper
    
    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }
    
    @Test
    fun `successfully call to get all companies endpoint`() {
        //given
        //when then
        mockMvc.perform(
            MockMvcRequestBuilders.get(Endpoint.GET_COMPANIES)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
    }
}