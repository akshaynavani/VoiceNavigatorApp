package com.cmpe277Hackathon.voicenavigator.core.storage.history

data class HistoryEntry(
    val id: String,
    val feature: String, // "dmv" | "esg"
    val query: String,
    val answer: String,
    val timestampMs: Long = System.currentTimeMillis()
)
