package com.shared.prices.tracker.infra.adapters.incoming.rest.controller

import com.shared.prices.tracker.domain.ports.incoming.LoadCompanyInfoUseCase
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.constant.Endpoint
import com.shared.prices.tracker.infra.adapters.incoming.rest.dto.request.GetCompanySharePriceRequestV1
import com.shared.prices.tracker.infra.adapters.incoming.rest.mapper.toCompanySharePriseResponse
import com.shared.prices.tracker.infra.adapters.incoming.rest.mapper.toQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanySharePriceController(
    private val handler: LoadCompanyInfoUseCase
) {
    
    @GetMapping(Endpoint.GET_SHARE_PRICES)
    fun getSharePrices(@PathVariable id: String, @RequestBody request: GetCompanySharePriceRequestV1) =
        handler.loadCompanyInfo(request.toQuery(id)).toCompanySharePriseResponse()
}