package com.cmpe277Hackathon.voicenavigator.core.voice.contract

interface VoiceInputManager {
    suspend fun startListening()
    suspend fun stopListening()
}
