package com.vista.memoryos.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val supabase: SupabaseClient
) : ViewModel() {

    private val _status = MutableStateFlow("Not Tested")
    val status: StateFlow<String> = _status

    fun testConnection() {
        viewModelScope.launch {
            try {
                supabase.postgrest["profiles"].select()
                _status.value = "Connection Successful"
            } catch (e: Exception) {
                _status.value = "Failed: ${e.message}"
            }
        }
    }
}