package com.cmpe277Hackathon.voicenavigator.core.llm.fake

import com.cmpe277Hackathon.voicenavigator.core.llm.contract.AnswerGenerator

/**
 * Stub implementation. Replace with real LLM call (e.g. Claude API).
 */
class FakeAnswerGenerator : AnswerGenerator {
    override suspend fun generateAnswer(prompt: String): String {
        // TODO: call Claude API with retrieved chunks as context
        return "Placeholder answer for: \"$prompt\""
    }
}
