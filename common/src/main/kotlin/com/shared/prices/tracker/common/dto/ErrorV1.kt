package com.shared.prices.tracker.common.dto

data class ErrorV1(
    val code: String,
    val message: String,
)

object ErrorCode {
    
    const val NOT_FOUND = "NotFound"
}