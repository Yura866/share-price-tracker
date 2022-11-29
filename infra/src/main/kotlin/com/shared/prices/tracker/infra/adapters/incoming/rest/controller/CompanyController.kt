package com.shared.prices.tracker.infra.adapters.incoming.rest.controller

import com.shared.prices.tracker.domain.ports.incoming.RetrieveCompanyUseCase
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.constant.Endpoint
import com.shared.prices.tracker.infra.adapters.incoming.rest.mapper.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanyController(
    private val handler: RetrieveCompanyUseCase,
) {
    
    @GetMapping(Endpoint.GET_COMPANIES)
    fun geCompanies() = handler.findAllCompanies().toResponse()
}