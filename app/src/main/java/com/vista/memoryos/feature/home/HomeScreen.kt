package com.vista.memoryos.feature.home

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vista.memoryos.domain.model.VistaFile
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    manualFileCaptureViewModel: ManualFileCaptureViewModel = hiltViewModel(),
    observeFilesViewModel: ObserveFilesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val status by viewModel.status.collectAsState()
    val captureState by manualFileCaptureViewModel.captureState.collectAsState()
    val files by observeFilesViewModel.files.collectAsState()

    val filePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenMultipleDocuments()
        ) { uris ->

            if (uris.isNotEmpty()) {
                uris.forEach { uri ->
                    try {
                        context.contentResolver.takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                    } catch (_: SecurityException) {
                        // Some document providers do not support
                        // persistable URI permissions.
                    }
                }

                manualFileCaptureViewModel.captureFiles(
                    context = context,
                    uris = uris
                )
            }
        }

    LaunchedEffect(captureState) {
        if (
            captureState is ManualFileCaptureState.Success ||
            captureState is ManualFileCaptureState.PartialSuccess
        ) {
            delay(3000)
            manualFileCaptureViewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "VISTA",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = status,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.testConnection() }
        ) {
            Text("Test Connection")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                filePickerLauncher.launch(arrayOf("*/*"))
            },
            enabled = captureState !is ManualFileCaptureState.Saving
        ) {
            Text("Add File")
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (val state = captureState) {

            ManualFileCaptureState.Idle -> Unit

            is ManualFileCaptureState.Saving -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text =
                            "Adding ${state.completed} of ${state.total} files..."
                    )

                    if (state.failed > 0) {
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${state.failed} file(s) failed",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            is ManualFileCaptureState.Success -> {
                Text(
                    text = "${state.count} file(s) added successfully",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is ManualFileCaptureState.PartialSuccess -> {
                Text(
                    text =
                        "${state.successful} of ${state.total} file(s) added. " +
                                "${state.failed} failed.",
                    color = MaterialTheme.colorScheme.error
                )
            }

            is ManualFileCaptureState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your Files",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (files.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No files captured yet.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = files,
                    key = { file -> file.fileId }
                ) { file ->
                    VistaFileCard(file = file)
                }
            }
        }
    }
}

@Composable
private fun VistaFileCard(file: VistaFile) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = file.displayName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "VISTA ID: ${file.fileId}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Type: ${file.mimeType}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Category: ${file.category.name}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Size: ${formatFileSize(file.sizeBytes)}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Upload: ${file.uploadStatus.name}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Processing: ${file.processingStatus.name}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

private fun formatFileSize(sizeBytes: Long): String {
    if (sizeBytes < 1024) return "$sizeBytes B"
    if (sizeBytes < 1024 * 1024) return "${sizeBytes / 1024} KB"
    if (sizeBytes < 1024 * 1024 * 1024) {
        return "${sizeBytes / (1024 * 1024)} MB"
    }

    return "${sizeBytes / (1024 * 1024 * 1024)} GB"
}