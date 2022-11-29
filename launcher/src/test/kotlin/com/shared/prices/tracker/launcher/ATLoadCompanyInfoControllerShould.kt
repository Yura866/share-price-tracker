package com.shared.prices.tracker.launcher

import com.fasterxml.jackson.databind.ObjectMapper
import com.shared.prices.tracker.domain.ports.incoming.RetrieveCompanyUseCase
import com.shared.prices.tracker.domain.ports.outgoing.CompanyRepositoryPort
import com.shared.prices.tracker.domain.service.cqs.DateUnit
import com.shared.prices.tracker.domain.service.mother.Casual
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.constant.Endpoint
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.request.GetCompanySharePriceRequestV1
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.response.CompanySharePriceResponseV1
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.CompanyRepository
import com.shared.prices.tracker.infra.adapters.outgoing.persistence.repository.SharePriceRepository
import io.kotest.matchers.shouldBe
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
import java.time.Clock
import java.util.*

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
internal class ATLoadCompanyInfoControllerShould {
    
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
    private lateinit var sharePriceRepository: SharePriceRepository
    
    @Autowired
    private lateinit var mapper: ObjectMapper
    
    @Autowired
    lateinit var clock: Clock
    
    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }
    
    @Test
    fun `successfully retrieve company share prices data of existing company`() {
        //given
        val givenCompanyId = UUID.nameUUIDFromBytes("Apple-1".toByteArray()).toString()
        val givenRequest = GetCompanySharePriceRequestV1(
            duration = 1,
            dateUnit = DateUnit.DAYS.name
        )
        //when
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get(Endpoint.GET_SHARE_PRICES, givenCompanyId)
                .content(mapper.writeValueAsString(givenRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        //then
        val response = mapper.readValue(result.response.contentAsString, CompanySharePriceResponseV1::class.java)
        
        response.sharePrices[0].companyId shouldBe givenCompanyId
    }
    
    @Test
    fun `throw bad request exception when data unit type is incorrect`() {
        //given
        val givenCompanyId = UUID.nameUUIDFromBytes("Apple-1".toByteArray()).toString()
        val givenRequest = GetCompanySharePriceRequestV1(
            duration = 1,
            dateUnit = Casual.string()
        )
        //when then
         mockMvc.perform(
            MockMvcRequestBuilders.get(Endpoint.GET_SHARE_PRICES, givenCompanyId)
                .content(mapper.writeValueAsString(givenRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
}