package com.cmpe277Hackathon.voicenavigator.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmpe277Hackathon.voicenavigator.core.ui.model.CitationUiModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnswerCard(
    answerText: String,
    citations: List<CitationUiModel>,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = answerText, style = MaterialTheme.typography.bodyLarge)
            if (citations.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Sources", style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    citations.forEach { citation ->
                        CitationChip(citation = citation)
                    }
                }
            }
        }
    }
}
