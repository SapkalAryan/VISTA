package com.vista.memoryos.domain.model

sealed class AuthError {

    data object InvalidEmail : AuthError()

    data object WeakPassword : AuthError()

    data object EmptyFields : AuthError()

    data object UserAlreadyExists : AuthError()

    data object NetworkError : AuthError()

    data object TooManyRequests : AuthError()

    data object EmailVerificationRequired : AuthError()

    data object Unknown : AuthError()
}