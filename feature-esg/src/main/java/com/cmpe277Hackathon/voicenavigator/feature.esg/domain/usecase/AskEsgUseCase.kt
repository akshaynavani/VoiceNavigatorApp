package com.cmpe277Hackathon.voicenavigator.feature.esg.domain.usecase

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer
import com.cmpe277Hackathon.voicenavigator.feature.esg.domain.repository.EsgRepository

class AskEsgUseCase(private val repository: EsgRepository) {
    suspend operator fun invoke(query: String): AppResult<Answer> {
        if (query.isBlank()) return AppResult.Error("Query must not be empty")
        // TODO: offline check, report-year filtering, input sanitization
        return repository.ask(query.trim())
    }
}
