package com.cmpe277Hackathon.voicenavigator.core.retrieval.model

import com.cmpe277Hackathon.voicenavigator.core.model.source.SourceReference

data class RetrievalResponse(
    val chunks: List<String>,
    val sources: List<SourceReference>
)
