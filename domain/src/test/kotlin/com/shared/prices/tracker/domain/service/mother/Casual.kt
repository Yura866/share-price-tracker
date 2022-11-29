package com.shared.prices.tracker.domain.service.mother

import java.util.*
import kotlin.math.absoluteValue

object Casual {
    
    fun uuid(): UUID = UUID.randomUUID()
    
    fun uuidStr() = uuid().toString()
    
    fun string() = (1..20)
        .map { uuidStr().replace("-", "").random() }
        .joinToString("")
    
    fun int() = Random().nextInt().absoluteValue
    
}