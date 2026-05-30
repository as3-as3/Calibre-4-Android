package com.calibre.android.service.conversion

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.calibre.android.model.ConversionTask
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversionService @Inject constructor(
    @ApplicationContext private val context: Context
) : IConversionService {
    
    private val workManager = WorkManager.getInstance(context)

    override suspend fun getSupportedConversions(): List<Pair<String, String>> {
        return listOf("EPUB" to "PDF", "PDF" to "EPUB")
    }

    override suspend fun isConversionSupported(sourceFormat: String, targetFormat: String): Boolean {
        return true
    }

    override suspend fun convertBook(bookId: String, targetFormat: String, options: ConversionOptions): ConversionTask {
        val requestId = UUID.randomUUID()
        val request = OneTimeWorkRequestBuilder<ConversionWorker>()
            .setInputData(workDataOf(
                "bookId" to bookId,
                "targetFormat" to targetFormat
            ))
            .setId(requestId)
            .build()
        
        workManager.enqueue(request)

        return ConversionTask(
            id = requestId.toString(),
            bookId = bookId,
            sourceFormat = "Unknown", // Should get from book
            targetFormat = targetFormat,
            status = "queued",
            progressPercent = 0,
            error = null,
            createdDate = System.currentTimeMillis(),
            completionDate = null
        )
    }

    override fun getConversionProgress(taskId: String): Flow<Int> {
        TODO("Observe workManager.getWorkInfoByIdFlow")
    }

    override suspend fun cancelConversion(taskId: String) {
        workManager.cancelWorkById(UUID.fromString(taskId))
    }

    override suspend fun getConversionHistory(): List<ConversionTask> {
        return emptyList()
    }

    override fun getActiveConversions(): Flow<List<ConversionTask>> {
        TODO("Implement using WorkManager")
    }

    override suspend fun clearHistory(olderThanDays: Int) {
        // Clear logic
    }
}
