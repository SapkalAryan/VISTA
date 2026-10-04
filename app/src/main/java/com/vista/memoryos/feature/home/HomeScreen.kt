package com.vista.memoryos.feature.home

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vista.memoryos.domain.model.FileCategory
import com.vista.memoryos.domain.model.FileProcessingStatus
import com.vista.memoryos.domain.model.FileUploadStatus
import com.vista.memoryos.domain.model.VistaFile
import java.util.Locale
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    manualFileCaptureViewModel: ManualFileCaptureViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val files by homeViewModel.files.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val errorMessage by homeViewModel.errorMessage.collectAsState()
    val captureState by manualFileCaptureViewModel.captureState.collectAsState()

    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf<FileCategory?>(null)
    }

    var selectedFile by remember {
        mutableStateOf<VistaFile?>(null)
    }

    var fileToDelete by remember {
        mutableStateOf<VistaFile?>(null)
    }

    val filePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenMultipleDocuments()
        ) { uris ->

            if (uris.isNotEmpty()) {
                takePersistablePermissions(
                    context = context,
                    uris = uris
                )

                manualFileCaptureViewModel.captureFiles(
                    context = context,
                    uris = uris
                )
            }
        }

    val filteredFiles = files.filter { file ->

        val matchesSearch =
            searchQuery.isBlank() ||
                    file.displayName.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    file.fileId.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    file.mimeType.contains(
                        searchQuery,
                        ignoreCase = true
                    )

        val matchesCategory =
            selectedCategory == null ||
                    file.category == selectedCategory

        matchesSearch && matchesCategory
    }

    LaunchedEffect(captureState) {
        when (captureState) {
            is ManualFileCaptureState.Success,
            is ManualFileCaptureState.PartialSuccess -> {
                // Room inventory automatically refreshes through
                // HomeViewModel's ObserveFilesUseCase.
            }

            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "VISTA Inventory",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    filePickerLauncher.launch(
                        arrayOf("*/*")
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add files"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchQuery = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                placeholder = {
                    Text("Search files...")
                },
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            CategoryFilter(
                selectedCategory = selectedCategory,
                onCategorySelected = {
                    selectedCategory = it
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            CaptureStatus(
                state = captureState,
                onDismiss = {
                    manualFileCaptureViewModel.resetState()
                }
            )

            if (isLoading) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else if (filteredFiles.isEmpty()) {

                EmptyInventory(
                    hasFilters =
                        searchQuery.isNotBlank() ||
                                selectedCategory != null,
                    onCaptureFiles = {
                        filePickerLauncher.launch(
                            arrayOf("*/*")
                        )
                    }
                )

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(
                        items = filteredFiles,
                        key = {
                            it.fileId
                        }
                    ) { file ->

                        VistaFileCard(
                            file = file,
                            onClick = {
                                selectedFile = file
                            },
                            onRetryUpload = {
                                manualFileCaptureViewModel
                                    .retryUpload(file)
                            },
                            onDelete = {
                                fileToDelete = file
                            }
                        )
                    }

                    item {
                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )
                    }
                }
            }
        }
    }

    errorMessage?.let { message ->

        AlertDialog(
            onDismissRequest = {
                homeViewModel.clearError()
            },
            title = {
                Text("Something went wrong")
            },
            text = {
                Text(message)
            },
            confirmButton = {
                Button(
                    onClick = {
                        homeViewModel.clearError()
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

    selectedFile?.let { file ->

        FileDetailsDialog(
            file = file,
            onDismiss = {
                selectedFile = null
            },
            onDelete = {
                selectedFile = null
                fileToDelete = file
            }
        )
    }

    fileToDelete?.let { file ->

        AlertDialog(
            onDismissRequest = {
                fileToDelete = null
            },
            title = {
                Text("Delete file?")
            },
            text = {
                Text(
                    "This will remove \"${file.displayName}\" from the VISTA inventory."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        homeViewModel.deleteFile(file)
                        fileToDelete = null
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        fileToDelete = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun CaptureStatus(
    state: ManualFileCaptureState,
    onDismiss: () -> Unit
) {
    when (state) {

        is ManualFileCaptureState.Saving -> {
            CardStatus(
                text = "Adding files: ${state.completed}/${state.total}"
            )
        }

        is ManualFileCaptureState.Retrying -> {
            CardStatus(
                text = "Retrying upload..."
            )
        }

        is ManualFileCaptureState.Success -> {
            CardStatus(
                text = "${state.count} file(s) added successfully",
                onDismiss = onDismiss
            )
        }

        is ManualFileCaptureState.RetrySuccess -> {
            CardStatus(
                text = "${state.fileName} uploaded successfully",
                onDismiss = onDismiss
            )
        }

        is ManualFileCaptureState.PartialSuccess -> {
            CardStatus(
                text = "${state.successful}/${state.total} files uploaded. ${state.failed} failed.",
                onDismiss = onDismiss
            )
        }

        is ManualFileCaptureState.Error -> {
            CardStatus(
                text = state.message,
                onDismiss = onDismiss
            )
        }

        ManualFileCaptureState.Idle -> Unit
    }
}

@Composable
private fun CardStatus(
    text: String,
    onDismiss: (() -> Unit)? = null
) {
    androidx.compose.material3.Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )

            onDismiss?.let {
                IconButton(
                    onClick = it
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss"
                    )
                }
            }
        }
    }

    Spacer(
        modifier = Modifier.height(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryFilter(
    selectedCategory: FileCategory?,
    onCategorySelected: (FileCategory?) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        OutlinedTextField(
            value = selectedCategory?.name ?: "All categories",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            DropdownMenuItem(
                text = {
                    Text("All categories")
                },
                onClick = {
                    onCategorySelected(null)
                    expanded = false
                }
            )

            FileCategory.entries.forEach { category ->

                DropdownMenuItem(
                    text = {
                        Text(category.name)
                    },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun VistaFileCard(
    file: VistaFile,
    onClick: () -> Unit,
    onRetryUpload: () -> Unit,
    onDelete: () -> Unit
) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                FileTypeIcon(
                    category = file.category
                )

                Spacer(
                    modifier = Modifier.size(12.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = file.displayName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = file.category.name,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete file"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "VISTA ID: ${file.fileId}",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = formatFileSize(file.sizeBytes),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            StatusRow(
                label = "Upload",
                value = file.uploadStatus.name
            )

            StatusRow(
                label = "Processing",
                value = file.processingStatus.name
            )

            if (file.uploadStatus == FileUploadStatus.FAILED) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = onRetryUpload,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Retry Upload")
                }
            }
        }
    }
}

@Composable
private fun StatusRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun FileTypeIcon(
    category: FileCategory
) {
    Icon(
        imageVector = Icons.Default.Add,
        contentDescription = category.name,
        modifier = Modifier.size(32.dp)
    )
}

@Composable
private fun EmptyInventory(
    hasFilters: Boolean,
    onCaptureFiles: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = if (hasFilters) {
                    "No matching files"
                } else {
                    "Your inventory is empty"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = if (hasFilters) {
                    "Try another search or category."
                } else {
                    "Add your first file to VISTA."
                },
                style = MaterialTheme.typography.bodyMedium
            )

            if (!hasFilters) {

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = onCaptureFiles
                ) {
                    Text("Add Files")
                }
            }
        }
    }
}

@Composable
private fun FileDetailsDialog(
    file: VistaFile,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = file.displayName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                DetailRow(
                    label = "VISTA ID",
                    value = file.fileId
                )

                DetailRow(
                    label = "Category",
                    value = file.category.name
                )

                DetailRow(
                    label = "MIME type",
                    value = file.mimeType
                )

                DetailRow(
                    label = "Size",
                    value = formatFileSize(file.sizeBytes)
                )

                DetailRow(
                    label = "Upload",
                    value = file.uploadStatus.name
                )

                DetailRow(
                    label = "Processing",
                    value = file.processingStatus.name
                )

                DetailRow(
                    label = "Cloud",
                    value = file.cloudPath ?: "Not uploaded"
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Close")
            }
        },
        dismissButton = {
            Button(
                onClick = onDelete
            ) {
                Text("Delete")
            }
        }
    )
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {
    Column {

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun takePersistablePermissions(
    context: Context,
    uris: List<Uri>
) {
    uris.forEach { uri ->
        try {
            context.contentResolver.takePersistableUriPermission(
                uri,
                android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        } catch (_: SecurityException) {
            // Some providers do not support persistable permissions.
        }
    }
}

private fun formatFileSize(
    sizeBytes: Long
): String {

    if (sizeBytes < 1024) {
        return "$sizeBytes B"
    }

    val kb = sizeBytes / 1024.0

    if (kb < 1024) {
        return String.format(
            Locale.US,
            "%.1f KB",
            kb
        )
    }

    val mb = kb / 1024.0

    if (mb < 1024) {
        return String.format(
            Locale.US,
            "%.1f MB",
            mb
        )
    }

    val gb = mb / 1024.0

    return String.format(
        Locale.US,
        "%.1f GB",
        gb
    )
}