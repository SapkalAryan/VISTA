
package com.vista.memoryos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vista.memoryos.core.navigation.VistaNavHost
import com.vista.memoryos.ui.theme.VISTATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VISTATheme {
                VistaNavHost()
            }
        }
    }
}