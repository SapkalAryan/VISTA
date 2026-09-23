package com.vista.memoryos.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vista.memoryos.core.theme.Spacing
import com.vista.memoryos.core.ui.VistaButton
import com.vista.memoryos.core.ui.VistaCard
import com.vista.memoryos.core.ui.VistaTopBar

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            VistaTopBar(title = "VISTA")
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {

            VistaCard {
                Text(
                    text = "Welcome to VISTA",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.height(Spacing.sm))

                Text(
                    text = "Your intelligent cloud memory system is now ready.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            VistaButton(
                text = "Upload Memory",
                onClick = {
                    // Coming in Module M2
                }
            )
        }
    }
}