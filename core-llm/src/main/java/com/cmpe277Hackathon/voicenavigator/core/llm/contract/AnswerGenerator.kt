package com.cmpe277Hackathon.voicenavigator.core.llm.contract

interface AnswerGenerator {
    suspend fun generateAnswer(prompt: String): String
}
