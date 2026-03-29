package com.cmpe277Hackathon.voicenavigator.core.model.answer

import com.cmpe277Hackathon.voicenavigator.core.model.source.SourceReference

data class Answer(
    val text: String,
    val citations: List<SourceReference> = emptyList()
)
