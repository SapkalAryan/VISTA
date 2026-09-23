package com.vista.memoryos.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vista.memoryos.core.theme.Spacing
import com.vista.memoryos.core.ui.VistaButton
import com.vista.memoryos.core.ui.VistaCard
import com.vista.memoryos.core.ui.VistaTopBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            VistaTopBar("VISTA")
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
                    "Welcome to VISTA",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.height(Spacing.sm))

                when {

                    state.isLoading -> {
                        CircularProgressIndicator()
                    }

                    state.error != null -> {
                        Text(state.error!!)
                    }

                    else -> {
                        Text(state.data ?: "")
                    }
                }
            }

            VistaButton(
                text = "Upload Memory",
                onClick = {}
            )
        }
    }
}