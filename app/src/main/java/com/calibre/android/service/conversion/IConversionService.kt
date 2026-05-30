package com.calibre.android.service.conversion

import com.calibre.android.model.ConversionTask
import kotlinx.coroutines.flow.Flow

interface IConversionService {
    suspend fun getSupportedConversions(): List<Pair<String, String>>
    suspend fun isConversionSupported(sourceFormat: String, targetFormat: String): Boolean
    suspend fun convertBook(bookId: String, targetFormat: String, options: ConversionOptions = ConversionOptions()): ConversionTask
    fun getConversionProgress(taskId: String): Flow<Int>
    suspend fun cancelConversion(taskId: String)
    suspend fun getConversionHistory(): List<ConversionTask>
    fun getActiveConversions(): Flow<List<ConversionTask>>
    suspend fun clearHistory(olderThanDays: Int = 30)
}

data class ConversionOptions(
    val compressionLevel: Int = 6,
    val imageQuality: Int = 85,
    val fontSize: Int = 12,
    val stripDRM: Boolean = false,
    val pageMargins: Int = 0
)

class UnsupportedConversionException(sourceFormat: String, targetFormat: String) : Exception("Conversion from $sourceFormat to $targetFormat not supported")
