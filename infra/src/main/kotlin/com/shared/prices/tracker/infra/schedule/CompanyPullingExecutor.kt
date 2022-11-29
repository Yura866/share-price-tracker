package com.shared.prices.tracker.infra.schedule

import com.shared.prices.tracker.domain.service.processor.CompanyPullProcessor
import org.springframework.scheduling.annotation.Scheduled

class CompanyPullingExecutor(
    private val processor: CompanyPullProcessor
) {
    
    @Scheduled(fixedDelayString = "\${company.pulling.execute.period.millis:20000}")
    fun run() {
        processor.doProcess()
    }
}