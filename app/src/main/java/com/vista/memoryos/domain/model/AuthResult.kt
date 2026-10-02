package com.vista.memoryos.domain.model

sealed class AuthResult<out T> {

    data class Success<T>(
        val data: T
    ) : AuthResult<T>()

    data class Failure(
        val error: AuthError
    ) : AuthResult<Nothing>()
}