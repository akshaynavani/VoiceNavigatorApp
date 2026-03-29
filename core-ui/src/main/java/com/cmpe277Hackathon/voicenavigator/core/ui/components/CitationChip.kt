package com.cmpe277Hackathon.voicenavigator.core.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cmpe277Hackathon.voicenavigator.core.ui.model.CitationUiModel

@Composable
fun CitationChip(
    citation: CitationUiModel,
    modifier: Modifier = Modifier
) {
    AssistChip(
        onClick = { /* TODO: open source detail sheet */ },
        label = {
            Text(
                text = citation.detail?.let { "${citation.title} · $it" } ?: citation.title,
                style = MaterialTheme.typography.labelSmall
            )
        },
        modifier = modifier
    )
}
