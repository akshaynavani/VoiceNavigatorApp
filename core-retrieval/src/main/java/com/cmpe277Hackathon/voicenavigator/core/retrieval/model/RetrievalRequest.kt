package com.cmpe277Hackathon.voicenavigator.core.retrieval.model

data class RetrievalRequest(
    val query: String,
    val corpus: String,
    val maxChunks: Int = 5
)
