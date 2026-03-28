package com.cmpe277Hackathon.voicenavigator.core.retrieval.search

interface RetrievalService {
    suspend fun search(query: String, corpus: String): List<String>
}
