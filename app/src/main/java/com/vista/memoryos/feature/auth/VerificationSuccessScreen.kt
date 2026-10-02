package com.vista.memoryos.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerificationSuccessScreen(
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Email verified!",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Your VISTA account has been successfully verified.",
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = onContinue,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Continue")
        }
    }
}