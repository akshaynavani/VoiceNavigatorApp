package com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.model

import com.cmpe277Hackathon.voicenavigator.core.ui.model.CitationUiModel

data class EsgUiState(
    val isListening: Boolean = false,
    val isLoading: Boolean = false,
    val query: String = "",
    val answerText: String = "",
    val citations: List<CitationUiModel> = emptyList(),
    val errorMessage: String? = null
)
