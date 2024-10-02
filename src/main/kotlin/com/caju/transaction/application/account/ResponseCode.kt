package com.caju.transaction.application.account

enum class ResponseCode(val value: String) {
    APPROVED("00"),
    INSUFFICIENT_FUNDS("51"),
    INTERNAL_ERROR("07")
}