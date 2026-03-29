package com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.ui.model.CitationUiModel
import com.cmpe277Hackathon.voicenavigator.core.voice.contract.VoiceInputManager
import com.cmpe277Hackathon.voicenavigator.core.voice.fake.FakeVoiceInputManager
import com.cmpe277Hackathon.voicenavigator.core.voice.model.VoiceState
import com.cmpe277Hackathon.voicenavigator.feature.esg.domain.repository.FakeEsgRepository
import com.cmpe277Hackathon.voicenavigator.feature.esg.domain.usecase.AskEsgUseCase
import com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.model.EsgUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EsgViewModel(
    private val voiceInputManager: VoiceInputManager = FakeVoiceInputManager()
) : ViewModel() {

    // TODO: inject repository via Hilt — using fake for skeleton
    private val useCase = AskEsgUseCase(FakeEsgRepository())

    private val _uiState = MutableStateFlow(EsgUiState())
    val uiState: StateFlow<EsgUiState> = _uiState.asStateFlow()

    init {
        // Observe voice state — auto-fill query on successful transcription
        viewModelScope.launch {
            voiceInputManager.voiceState.collect { state ->
                when (state) {
                    VoiceState.Idle -> _uiState.value = _uiState.value.copy(isListening = false)
                    VoiceState.Listening -> _uiState.value = _uiState.value.copy(isListening = true, errorMessage = null)
                    VoiceState.Processing -> _uiState.value = _uiState.value.copy(isListening = false)
                    is VoiceState.Result -> _uiState.value = _uiState.value.copy(
                        isListening = false,
                        query = state.text
                    )
                    is VoiceState.Error -> _uiState.value = _uiState.value.copy(
                        isListening = false,
                        errorMessage = state.message
                    )
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query, errorMessage = null)
    }

    fun onAsk() {
        val query = _uiState.value.query
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            when (val result = useCase(query)) {
                is AppResult.Success -> {
                    val answer = result.data
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        answerText = answer.text,
                        citations = answer.citations.map { src ->
                            CitationUiModel(
                                title = src.title,
                                detail = src.page?.let { "p.$it" }
                            )
                        }
                    )
                }
                is AppResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun onVoiceToggle() {
        if (_uiState.value.isListening) {
            voiceInputManager.stopListening()
        } else {
            voiceInputManager.startListening()
        }
    }

    fun onVoicePermissionDenied() {
        _uiState.value = _uiState.value.copy(
            errorMessage = "Microphone permission is required for voice input"
        )
    }
}
