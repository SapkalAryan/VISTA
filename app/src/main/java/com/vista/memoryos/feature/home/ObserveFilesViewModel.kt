package com.vista.memoryos.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vista.memoryos.domain.model.VistaFile
import com.vista.memoryos.domain.usecase.ObserveFilesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ObserveFilesViewModel @Inject constructor(
    observeFilesUseCase: ObserveFilesUseCase
) : ViewModel() {

    val files: StateFlow<List<VistaFile>> =
        observeFilesUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}