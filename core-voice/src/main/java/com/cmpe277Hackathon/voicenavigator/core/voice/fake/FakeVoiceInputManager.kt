package com.cmpe277Hackathon.voicenavigator.core.voice.fake

import com.cmpe277Hackathon.voicenavigator.core.voice.contract.VoiceInputManager
import com.cmpe277Hackathon.voicenavigator.core.voice.model.VoiceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Stub implementation for UI testing without a real microphone.
 * TODO: replace with RealVoiceInputManager backed by Android SpeechRecognizer.
 */
class FakeVoiceInputManager : VoiceInputManager {

    private val _voiceState = MutableStateFlow<VoiceState>(VoiceState.Idle)
    override val voiceState: StateFlow<VoiceState> = _voiceState.asStateFlow()

    override fun startListening() {
        _voiceState.value = VoiceState.Listening
        // TODO: emit VoiceState.Result("transcribed text") when recognition completes
    }

    override fun stopListening() {
        _voiceState.value = VoiceState.Idle
        // TODO: cancel SpeechRecognizer session
    }
}
