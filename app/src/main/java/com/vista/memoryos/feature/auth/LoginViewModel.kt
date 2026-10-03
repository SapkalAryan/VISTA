package com.vista.memoryos.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vista.memoryos.domain.model.AuthError
import com.vista.memoryos.domain.model.AuthResult
import com.vista.memoryos.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    fun login(email: String, password: String) {
        if (_loading.value) return

        _loginSuccess.value = false

        if (email.isBlank()) {
            _message.value = "Please enter your email."
            return
        }

        if (password.isBlank()) {
            _message.value = "Please enter your password."
            return
        }

        viewModelScope.launch {
            _loading.value = true

            val result = repository.login(email, password)

            _loading.value = false

            _message.value = when (result) {
                is AuthResult.Success -> {
                    _loginSuccess.value = true
                    "Login successful."
                }

                is AuthResult.Failure ->
                    when (result.error) {
                        AuthError.InvalidEmail ->
                            "Please enter a valid email address."

                        AuthError.WeakPassword ->
                            "Password is too weak."

                        AuthError.EmptyFields ->
                            "Please enter your email and password."

                        AuthError.UserAlreadyExists ->
                            "This email is already registered."

                        AuthError.InvalidCredentials ->
                            "Invalid email or password."

                        AuthError.EmailNotVerified ->
                            "Please verify your email before logging in."

                        AuthError.NetworkError ->
                            "No internet connection."

                        AuthError.TooManyRequests ->
                            "Too many login attempts. Please try again later."

                        AuthError.EmailVerificationRequired ->
                            "Please verify your email before logging in."

                        AuthError.Unknown ->
                            "Something went wrong. Please try again."
                    }
            }
        }
    }
}