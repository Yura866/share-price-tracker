package com.shared.prices.tracker.domain.exception

class BadRequestException(val errors: Map<String, String>) :
    RuntimeException(errors.values.joinToString { it }) {
    
    constructor(code: String, message: String) : this(mapOf(code to message))
}

object BadRequestCode {
    
    const val UNKNOWN_TIME_UNIT = "unknownTimeUnit"
}