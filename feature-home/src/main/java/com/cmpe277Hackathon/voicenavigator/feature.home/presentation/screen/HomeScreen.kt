package com.cmpe277Hackathon.voicenavigator.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmpe277Hackathon.voicenavigator.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onNavigateToDmv: () -> Unit,
    onNavigateToEsg: () -> Unit,
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Voice Navigator",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Choose a navigator to get started",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = onNavigateToDmv,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("DMV Voice Navigator")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNavigateToEsg,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ESG Report Navigator")
        }
    }
}
