package com.vista.memoryos.data.repository

import com.vista.memoryos.domain.model.AuthError
import com.vista.memoryos.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
) : AuthRepository {

    override suspend fun register(
        email: String,
        password: String
    ): Result<AuthError?> {

        return try {

            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }

            Result.success(null)

        } catch (e: Exception) {

            Result.success(mapError(e.message.orEmpty()))
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthError?> {

        return try {

            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            Result.success(null)

        } catch (e: Exception) {

            Result.success(mapError(e.message.orEmpty()))
        }
    }

    override suspend fun logout() {
        supabase.auth.signOut()
    }

    /**
     * Converts Supabase exceptions into domain errors.
     */
    private fun mapError(message: String): AuthError {

        val text = message.lowercase()

        return when {

            "already registered" in text ->
                AuthError.EMAIL_EXISTS

            "rate_limit" in text ->
                AuthError.EMAIL_RATE_LIMIT

            "invalid email" in text ->
                AuthError.INVALID_EMAIL

            "password" in text ->
                AuthError.WEAK_PASSWORD

            "network" in text ->
                AuthError.NETWORK

            else ->
                AuthError.UNKNOWN
        }
    }
}