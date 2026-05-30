package com.calibre.android.service.settings

import kotlinx.coroutines.flow.Flow

interface ISettingsService {
    fun getSetting(key: String, defaultValue: String = ""): Flow<String>
    fun getBooleanSetting(key: String, defaultValue: Boolean = false): Flow<Boolean>
    fun getIntSetting(key: String, defaultValue: Int = 0): Flow<Int>
    suspend fun setSetting(key: String, value: String)
    suspend fun setBooleanSetting(key: String, value: Boolean)
    suspend fun setIntSetting(key: String, value: Int)
    fun getLibraryPath(): Flow<String>
    suspend fun setLibraryPath(path: String)
    fun getThemeMode(): Flow<String>
    suspend fun setThemeMode(mode: String)
    fun getAllSettings(): Flow<Map<String, String>>
    suspend fun resetToDefaults()
    suspend fun exportSettings(): String
    suspend fun importSettings(jsonSettings: String)
}
