package com.cmpe277Hackathon.voicenavigator.feature.dmv.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmpe277Hackathon.voicenavigator.core.voice.contract.VoiceInputManager

class DmvViewModelFactory(
    private val voiceInputManager: VoiceInputManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DmvViewModel(voiceInputManager) as T
}
