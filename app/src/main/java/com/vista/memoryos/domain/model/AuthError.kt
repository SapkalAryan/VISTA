package com.vista.memoryos.domain.model

sealed class AuthError {

    // Registration errors
    data object InvalidEmail : AuthError()
    data object WeakPassword : AuthError()
    data object EmptyFields : AuthError()
    data object UserAlreadyExists : AuthError()
    data object EmailVerificationRequired : AuthError()

    // Login errors
    data object InvalidCredentials : AuthError()
    data object EmailNotVerified : AuthError()

    // Common errors
    data object NetworkError : AuthError()
    data object TooManyRequests : AuthError()
    data object Unknown : AuthError()
}