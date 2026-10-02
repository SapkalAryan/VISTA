package com.vista.memoryos.domain.repository

import com.vista.memoryos.domain.model.AuthResult

interface AuthRepository {

    suspend fun register(
        email: String,
        password: String
    ): AuthResult<Unit>

    suspend fun login(
        email: String,
        password: String
    ): AuthResult<Unit>

    suspend fun logout()
}