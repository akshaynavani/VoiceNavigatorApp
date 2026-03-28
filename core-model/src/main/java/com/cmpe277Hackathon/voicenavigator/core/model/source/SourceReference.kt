package com.cmpe277Hackathon.voicenavigator.core.model.source

data class SourceReference(
    val documentId: String,
    val title: String,
    val page: Int? = null,
    val section: String? = null
)
