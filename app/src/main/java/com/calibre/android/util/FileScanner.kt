package com.calibre.android.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File

class FileScanner(private val context: Context) {

    fun scanForBooks(directory: File = Environment.getExternalStorageDirectory()): List<File> {
        val books = mutableListOf<File>()
        val supportedExtensions = listOf("epub", "pdf", "mobi", "azw", "azw3")

        directory.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                books.addAll(scanForBooks(file))
            } else if (file.extension.lowercase() in supportedExtensions) {
                books.add(file)
            }
        }
        return books
    }
}
