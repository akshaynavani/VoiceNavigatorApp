package com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.cmpe277Hackathon.voicenavigator.core.ui.components.AnswerCard
import com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.viewmodel.EsgViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsgAskScreen(
    viewModel: EsgViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) viewModel.onVoiceToggle()
        else viewModel.onVoicePermissionDenied()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ESG Report Navigator") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("← Back") }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = state.query,
                onValueChange = viewModel::onQueryChanged,
                label = { Text("Ask an ESG question…") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 1,
                maxLines = 3
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = viewModel::onAsk,
                    enabled = !state.isLoading && state.query.isNotBlank(),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (state.isLoading) "Thinking…" else "Ask")
                }
                FilledTonalButton(
                    onClick = {
                        if (state.isListening) {
                            viewModel.onVoiceToggle()
                        } else {
                            val granted = ContextCompat.checkSelfPermission(
                                context, Manifest.permission.RECORD_AUDIO
                            ) == PackageManager.PERMISSION_GRANTED
                            if (granted) viewModel.onVoiceToggle()
                            else permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                        }
                    }
                ) {
                    Text(if (state.isListening) "■ Stop" else "🎤 Speak")
                }
            }
            state.errorMessage?.let { err ->
                Text(text = err, color = MaterialTheme.colorScheme.error)
            }
            if (state.answerText.isNotEmpty()) {
                AnswerCard(
                    answerText = state.answerText,
                    citations = state.citations
                )
            }
        }
    }
}
