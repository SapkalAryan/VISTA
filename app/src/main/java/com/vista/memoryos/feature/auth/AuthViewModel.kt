package com.vista.memoryos.feature.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import javax.inject.Inject
import com.vista.memoryos.data.remote.SupabaseClientProvider

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    val sessionStatus = SupabaseClientProvider.client.auth.sessionStatus
}