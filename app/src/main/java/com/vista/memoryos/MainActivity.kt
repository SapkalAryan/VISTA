package com.vista.memoryos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vista.memoryos.core.navigation.VistaNavHost
import com.vista.memoryos.data.remote.SupabaseClientProvider
import com.vista.memoryos.ui.theme.VISTATheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.auth.handleDeeplinks


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isAuthDeepLink = intent?.data?.scheme == "vista" &&
                intent?.data?.host == "auth"

        SupabaseClientProvider.client.handleDeeplinks(intent)

        setContent {
            VISTATheme {
                VistaNavHost(
                    isAuthDeepLink = isAuthDeepLink
                )
            }
        }
    }
}