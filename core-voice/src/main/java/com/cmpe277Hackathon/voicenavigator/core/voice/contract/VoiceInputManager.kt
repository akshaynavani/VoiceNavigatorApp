package com.cmpe277Hackathon.voicenavigator.core.voice.contract

import com.cmpe277Hackathon.voicenavigator.core.voice.model.VoiceState
import kotlinx.coroutines.flow.StateFlow

/**
 * Contract for voice input.
 *
 * Core owner: implement with Android SpeechRecognizer, emit state via [voiceState].
 * Feature owners: observe [voiceState]; on [VoiceState.Result] push text into the query field.
 */
interface VoiceInputManager {
    /** Current voice recognition state. Observe this in ViewModels. */
    val voiceState: StateFlow<VoiceState>

    fun startListening()
    fun stopListening()
}
