package com.shared.prices.tracker.launcher.config.infra.adapters


import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.MarketstackApiClient
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.fake.MarketstackApiFakeClient
import com.shared.prices.tracker.infra.adapters.outgoing.marketstack_api.client.http.MarketstackApiHttpClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MarketstackApiConfig {
    
    @ConditionalOnProperty(value = ["marketstack.client.type"], havingValue = "HTTP")
    class TmateHttpConfig {
        
        @Bean
        fun marketstackApiClient(): MarketstackApiClient = MarketstackApiHttpClient()
    }
    
    @Bean
    @ConditionalOnProperty(
        value = ["marketstack.client.type"], havingValue = "FAKE", matchIfMissing = true
    )
    fun marketstackApiClient(): MarketstackApiClient = MarketstackApiFakeClient()
}