package com.vista.memoryos.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vista.memoryos.domain.model.AuthError
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

            _message.value = when (result.getOrNull()) {

                null ->
                    "Verification email sent. Please check your inbox."

                AuthError.EMAIL_EXISTS ->
                    "This email is already registered. Please sign in."

                AuthError.EMAIL_RATE_LIMIT ->
                    "Too many verification emails were requested. Please wait a few minutes."

                AuthError.INVALID_EMAIL ->
                    "Please enter a valid email address."

                AuthError.WEAK_PASSWORD ->
                    "Password is too weak."

                AuthError.NETWORK ->
                    "No internet connection."

                AuthError.UNKNOWN ->
                    "Something went wrong. Please try again."
            }
        }
    }
}