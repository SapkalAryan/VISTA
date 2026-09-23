package com.vista.memoryos.feature.home

import androidx.lifecycle.viewModelScope
import com.vista.memoryos.core.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<String>() {

    init {
        loadHome()
    }

    private fun loadHome() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true
            )

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                data = "Your intelligent cloud memory system is now ready."
            )
        }
    }
}