package com.cmpe277Hackathon.voicenavigator.core.retrieval.coordinator

import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer
import com.cmpe277Hackathon.voicenavigator.core.retrieval.model.RetrievalRequest

/**
 * Orchestrates retrieval + LLM answering into a single grounded Answer.
 * Implementations must only produce answers grounded in the provided corpus.
 */
interface RetrievalCoordinator {
    suspend fun retrieve(request: RetrievalRequest): Answer
}
