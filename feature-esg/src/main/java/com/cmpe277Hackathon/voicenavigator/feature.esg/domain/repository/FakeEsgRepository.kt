package com.cmpe277Hackathon.voicenavigator.feature.esg.domain.repository

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer
import com.cmpe277Hackathon.voicenavigator.core.model.source.SourceReference

/**
 * Fake implementation for skeleton wiring and UI testing.
 * TODO: replace with real retrieval + LLM pipeline wired via DI.
 */
class FakeEsgRepository : EsgRepository {
    override suspend fun ask(query: String): AppResult<Answer> {
        val fakeCitation = SourceReference(
            documentId = "sofi_2024",
            title = "SOFI Report 2024",
            page = 18,
            section = "Chapter 2 – ESG Metrics"
        )
        return AppResult.Success(
            Answer(
                text = "Placeholder ESG answer for: \"$query\"",
                citations = listOf(fakeCitation)
            )
        )
    }
}
