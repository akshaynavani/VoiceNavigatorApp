package com.cmpe277Hackathon.voicenavigator.feature.esg.domain.repository

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult
import com.cmpe277Hackathon.voicenavigator.core.model.answer.Answer

interface EsgRepository {
    /**
     * Ask an ESG question. Answers must be grounded in approved ESG reports only.
     */
    suspend fun ask(query: String): AppResult<Answer>
}
