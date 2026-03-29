package com.cmpe277Hackathon.voicenavigator.feature.dmv.domain.repository

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer
import com.cmpe277Hackathon.voicenavigator.core.model.source.SourceReference

/**
 * Fake implementation for skeleton wiring and UI testing.
 * TODO: replace with real retrieval + LLM pipeline wired via DI.
 */
class FakeDmvRepository : DmvRepository {
    override suspend fun ask(query: String): AppResult<Answer> {
        val fakeCitation = SourceReference(
            documentId = "ca_dmv_handbook",
            title = "CA DMV Driver Handbook",
            page = 42,
            section = "Section 3 – Traffic Controls"
        )
        return AppResult.Success(
            Answer(
                text = "Placeholder DMV answer for: \"$query\"",
                citations = listOf(fakeCitation)
            )
        )
    }
}
