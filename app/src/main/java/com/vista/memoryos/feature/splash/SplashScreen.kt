
package com.vista.memoryos.feature.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(onContinue: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("VISTA", style = MaterialTheme.typography.displaySmall)
            Spacer(Modifier.height(12.dp))
            Button(onClick = onContinue) {
                Text("Start")
            }
        }
    }
}