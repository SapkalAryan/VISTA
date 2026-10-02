package com.vista.memoryos.feature.onboarding

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
class OnboardingViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    fun register(
        email: String,
        password: String
    ) {

        if (_loading.value) return

        if (email.isBlank()) {
            _message.value = "Please enter your email."
            return
        }

        if (password.length < 6) {
            _message.value = "Password must contain at least 6 characters."
            return
        }

        viewModelScope.launch {

            _loading.value = true

            val result = repository.register(email, password)

            _loading.value = false

            _message.value = when (result) {

                is AuthResult.Success ->
                    "Verification email sent. Please check your inbox."

                is AuthResult.Failure ->
                    when (result.error) {

                        AuthError.InvalidEmail ->
                            "Please enter a valid email address."

                        AuthError.WeakPassword ->
                            "Password is too weak."

                        AuthError.EmptyFields ->
                            "Please fill in all required fields."

                        AuthError.UserAlreadyExists ->
                            "This email is already registered. Please sign in."

                        AuthError.NetworkError ->
                            "No internet connection."

                        AuthError.TooManyRequests ->
                            "Too many verification emails were requested. Please wait a few minutes."

                        AuthError.EmailVerificationRequired ->
                            "Please verify your email before continuing."

                        AuthError.Unknown ->
                            "Something went wrong. Please try again."
                    }
            }
        }
    }
}