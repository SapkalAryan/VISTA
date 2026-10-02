package com.vista.memoryos.data.repository

import com.vista.memoryos.domain.model.AuthError
import com.vista.memoryos.domain.model.AuthResult
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
    ): AuthResult<Unit> {

        return try {

            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }

            AuthResult.Success(Unit)

        } catch (e: Exception) {

            AuthResult.Failure(
                mapError(e.message.orEmpty())
            )
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): AuthResult<Unit> {

        return try {

            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }

            AuthResult.Success(Unit)

        } catch (e: Exception) {

            AuthResult.Failure(
                mapError(e.message.orEmpty())
            )
        }
    }

    override suspend fun logout() {
        supabase.auth.signOut()
    }

    /**
     * Converts Supabase exceptions into domain-level authentication errors.
     */
    private fun mapError(message: String): AuthError {

        val text = message.lowercase()

        return when {

            "already registered" in text ->
                AuthError.UserAlreadyExists

            "rate_limit" in text ->
                AuthError.TooManyRequests

            "invalid email" in text ->
                AuthError.InvalidEmail

            "password" in text ->
                AuthError.WeakPassword

            "network" in text ||
                    "unable to resolve host" in text ||
                    "no address associated with hostname" in text ||
                    "failed to connect" in text ||
                    "connection refused" in text ||
                    "connection reset" in text ||
                    "timeout" in text ->
                AuthError.NetworkError

            else ->
                AuthError.Unknown
        }
    }
}