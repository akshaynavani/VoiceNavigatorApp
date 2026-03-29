package com.cmpe277Hackathon.voicenavigator.core.storage.history

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult

interface HistoryRepository {
    suspend fun getHistory(feature: String? = null): AppResult<List<HistoryEntry>>
    suspend fun saveEntry(entry: HistoryEntry): AppResult<Unit>
    suspend fun clearHistory(feature: String? = null): AppResult<Unit>
}
