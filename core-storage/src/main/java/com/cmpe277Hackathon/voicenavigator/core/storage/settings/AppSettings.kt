package com.cmpe277Hackathon.voicenavigator.core.storage.settings

data class AppSettings(
    val voiceLanguage: String = "en-US",
    val maxAnswerLength: Int = 300,
    val showCitations: Boolean = true
)
