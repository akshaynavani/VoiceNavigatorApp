package com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmpe277Hackathon.voicenavigator.core.voice.contract.VoiceInputManager

class EsgViewModelFactory(
    private val voiceInputManager: VoiceInputManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        EsgViewModel(voiceInputManager) as T
}
