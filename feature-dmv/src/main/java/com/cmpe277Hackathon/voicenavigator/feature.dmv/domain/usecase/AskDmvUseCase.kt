package com.cmpe277Hackathon.voicenavigator.feature.dmv.domain.usecase

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer
import com.cmpe277Hackathon.voicenavigator.feature.dmv.domain.repository.DmvRepository

class AskDmvUseCase(private val repository: DmvRepository) {
    suspend operator fun invoke(query: String): AppResult<Answer> {
        if (query.isBlank()) return AppResult.Error("Query must not be empty")
        // TODO: offline check, rate limiting, input sanitization
        return repository.ask(query.trim())
    }
}
