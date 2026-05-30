package com.calibre.android.format

import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FormatHandlerRegistry @Inject constructor() {
    private val handlers = listOf(
        EPUBHandler(),
        PDFHandler()
    )

    fun getHandler(file: File): IFormatHandler? {
        return handlers.find { it.supports(file) }
    }

    fun getHandler(format: String): IFormatHandler? {
        return handlers.find { it.supportsFormat(format) }
    }
}
