package com.cmpe277Hackathon.voicenavigator.core.storage.settings

import com.cmpe277Hackathon.voicenavigator.core.common.result.AppResult

interface SettingsRepository {
    suspend fun getSettings(): AppResult<AppSettings>
    suspend fun saveSettings(settings: AppSettings): AppResult<Unit>
}
