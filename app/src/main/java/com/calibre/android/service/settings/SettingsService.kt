package com.calibre.android.service.settings

import com.calibre.android.database.CalibreDatabase
import com.calibre.android.database.entity.SettingEntity
import com.calibre.android.database.entity.SettingKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsService @Inject constructor(
    private val database: CalibreDatabase
) : ISettingsService {
    
    private val settingDao = database.settingDao()

    override fun getSetting(key: String, defaultValue: String): Flow<String> {
        return settingDao.get(key).map { it?.value ?: defaultValue }
    }

    override fun getBooleanSetting(key: String, defaultValue: Boolean): Flow<Boolean> {
        return settingDao.get(key).map { it?.value?.toBoolean() ?: defaultValue }
    }

    override fun getIntSetting(key: String, defaultValue: Int): Flow<Int> {
        return settingDao.get(key).map { it?.value?.toInt() ?: defaultValue }
    }

    override suspend fun setSetting(key: String, value: String) {
        settingDao.insert(SettingEntity(key = key, value = value))
    }

    override suspend fun setBooleanSetting(key: String, value: Boolean) {
        setSetting(key, value.toString())
    }

    override suspend fun setIntSetting(key: String, value: Int) {
        setSetting(key, value.toString())
    }

    override fun getLibraryPath(): Flow<String> {
        return getSetting(SettingKeys.LIBRARY_PATH, "/storage/emulated/0/Calibre")
    }

    override suspend fun setLibraryPath(path: String) {
        setSetting(SettingKeys.LIBRARY_PATH, path)
    }

    override fun getThemeMode(): Flow<String> {
        return getSetting(SettingKeys.THEME_MODE, "system")
    }

    override suspend fun setThemeMode(mode: String) {
        setSetting(SettingKeys.THEME_MODE, mode)
    }

    override fun getAllSettings(): Flow<Map<String, String>> {
        return settingDao.getAll().map { list -> list.associate { it.key to it.value } }
    }

    override suspend fun resetToDefaults() {
        // Delete all
    }

    override suspend fun exportSettings(): String {
        return "{}"
    }

    override suspend fun importSettings(jsonSettings: String) {
        // Import
    }
}
