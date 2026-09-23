package com.vista.memoryos.core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T> : ViewModel() {

    protected val _uiState = MutableStateFlow(UiState<T>())

    val uiState: StateFlow<UiState<T>> =
        _uiState.asStateFlow()
}