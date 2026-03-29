package com.cmpe277Hackathon.voicenavigator.core.voice.impl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.cmpe277Hackathon.voicenavigator.core.voice.contract.VoiceInputManager
import com.cmpe277Hackathon.voicenavigator.core.voice.model.VoiceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Production implementation backed by Android SpeechRecognizer.
 * Must be called from the main thread (matches Compose button-click thread).
 *
 * Requires: RECORD_AUDIO permission + Google Play Services on device/emulator.
 */
class RealVoiceInputManager(private val context: Context) : VoiceInputManager {

    private val _voiceState = MutableStateFlow<VoiceState>(VoiceState.Idle)
    override val voiceState: StateFlow<VoiceState> = _voiceState.asStateFlow()

    private var recognizer: SpeechRecognizer? = null

    // Holds the last partial transcript as a fallback if final result returns NO_MATCH
    private var lastPartialResult: String = ""

    override fun startListening() {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            _voiceState.value = VoiceState.Error("Speech recognition not available on this device")
            return
        }

        lastPartialResult = ""
        destroyRecognizer()

        recognizer = SpeechRecognizer.createSpeechRecognizer(context).also { sr ->
            sr.setRecognitionListener(object : RecognitionListener {

                override fun onReadyForSpeech(params: Bundle?) {
                    _voiceState.value = VoiceState.Listening
                }

                override fun onBeginningOfSpeech() {}

                override fun onRmsChanged(rmsdB: Float) {}

                override fun onBufferReceived(buffer: ByteArray?) {}

                override fun onEndOfSpeech() {
                    _voiceState.value = VoiceState.Processing
                }

                override fun onPartialResults(partialResults: Bundle?) {
                    // Cache partial text — used as fallback if final result is empty
                    val partial = partialResults
                        ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        ?.firstOrNull()
                        .orEmpty()
                    if (partial.isNotBlank()) lastPartialResult = partial
                }

                override fun onResults(results: Bundle?) {
                    val text = results
                        ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        ?.firstOrNull()
                        .orEmpty()
                        .ifBlank { lastPartialResult } // fall back to partial if final is empty

                    destroyRecognizer()
                    _voiceState.value = if (text.isNotBlank()) {
                        VoiceState.Result(text)
                    } else {
                        VoiceState.Error("Couldn't hear you — please try again")
                    }
                }

                override fun onError(error: Int) {
                    // On NO_MATCH: if we captured a partial result, use it instead of failing
                    if (error == SpeechRecognizer.ERROR_NO_MATCH && lastPartialResult.isNotBlank()) {
                        destroyRecognizer()
                        _voiceState.value = VoiceState.Result(lastPartialResult)
                        return
                    }

                    val message = when (error) {
                        SpeechRecognizer.ERROR_AUDIO -> "Audio recording error — check microphone"
                        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Microphone permission denied"
                        SpeechRecognizer.ERROR_NETWORK,
                        SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network unavailable — check connection"
                        SpeechRecognizer.ERROR_NO_MATCH -> "Couldn't understand — speak clearly and try again"
                        SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognizer busy — try again"
                        SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech detected — tap Speak and try again"
                        SpeechRecognizer.ERROR_SERVER -> "Server error — check internet connection"
                        SpeechRecognizer.ERROR_CLIENT -> "Recognition client error — restart the app"
                        else -> "Voice error ($error)"
                    }
                    destroyRecognizer()
                    _voiceState.value = VoiceState.Error(message)
                }

                override fun onEvent(eventType: Int, params: Bundle?) {}
            })

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                // Hardcode en-US — avoids NO_MATCH on non-English locale emulators
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US")
                // Return partial results so we have a fallback if final result fails
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
                // Allow longer silence before cutting off
                putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500L)
                putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1500L)
                putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 500L)
                // Required by some Google Speech Service versions
                putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
            }

            _voiceState.value = VoiceState.Listening
            sr.startListening(intent)
        }
    }

    override fun stopListening() {
        destroyRecognizer()
        _voiceState.value = VoiceState.Idle
    }

    private fun destroyRecognizer() {
        recognizer?.stopListening()
        recognizer?.destroy()
        recognizer = null
    }
}
