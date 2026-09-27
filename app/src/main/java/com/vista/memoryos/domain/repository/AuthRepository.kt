package com.vista.memoryos.domain.repository

import com.vista.memoryos.domain.model.AuthError

interface AuthRepository {

    suspend fun register(
        email: String,
        password: String
    ): Result<AuthError?>

    suspend fun login(
        email: String,
        password: String
    ): Result<AuthError?>

    suspend fun logout()
}