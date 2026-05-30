package com.calibre.android.service.device

import com.calibre.android.model.Device
import com.calibre.android.model.SyncTask
import kotlinx.coroutines.flow.Flow

interface IDeviceSyncService {
    fun getAvailableDevices(): Flow<List<Device>>
    suspend fun syncBook(bookId: String, deviceId: String, targetFormat: String? = null): SyncTask
    suspend fun syncBooks(bookIds: List<String>, deviceId: String): List<SyncTask>
    fun getSyncProgress(taskId: String): Flow<Int>
    suspend fun cancelSync(taskId: String)
    suspend fun getSyncHistory(deviceId: String? = null): List<SyncTask>
    suspend fun getDeviceLibrary(deviceId: String): List<String>
    suspend fun removeFromDevice(bookId: String, deviceId: String)
}

class DeviceNotFoundException(message: String) : Exception(message)
class SyncException(message: String) : Exception(message)
