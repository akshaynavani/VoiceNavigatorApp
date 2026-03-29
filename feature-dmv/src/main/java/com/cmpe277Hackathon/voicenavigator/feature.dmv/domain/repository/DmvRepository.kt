package com.cmpe277Hackathon.voicenavigator.feature.dmv.domain.repository

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer

interface DmvRepository {
    /**
     * Ask a DMV question. Answers must be grounded in DMV-approved sources only.
     */
    suspend fun ask(query: String): AppResult<Answer>
}
