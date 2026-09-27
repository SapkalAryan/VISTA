package com.vista.memoryos.domain.model

enum class AuthError {
    INVALID_EMAIL,
    WEAK_PASSWORD,
    EMAIL_EXISTS,
    EMAIL_RATE_LIMIT,
    NETWORK,
    UNKNOWN
}