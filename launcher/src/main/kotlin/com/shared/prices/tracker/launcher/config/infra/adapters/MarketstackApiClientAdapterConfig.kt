package com.shared.prices.tracker.launcher.config.infra.adapters


import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.MarketstackApiClientAdapter
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.MarketstackApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MarketstackApiClientAdapterConfig {
    
    @Bean
    fun marketstackApiClientAdapter(marketstackApiClient: MarketstackApiClient) =
        MarketstackApiClientAdapter(marketstackApiClient)
}