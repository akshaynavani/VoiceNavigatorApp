package com.cmpe277Hackathon.voicenavigator.core.ui.model

/**
 * Pure display model for a citation chip.
 * Intentionally decoupled from core-model's SourceReference so
 * each feature module can map independently.
 */
data class CitationUiModel(
    val title: String,
    val detail: String? = null
)
