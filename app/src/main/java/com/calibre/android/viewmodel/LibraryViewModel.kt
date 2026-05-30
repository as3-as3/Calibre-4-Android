package com.calibre.android.viewmodel

import androidx.lifecycle.viewModelScope
import com.calibre.android.model.Book
import com.calibre.android.service.library.ILibraryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryService: ILibraryService
) : BaseViewModel() {
    
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()
    
    init {
        loadBooks()
    }
    
    private fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                libraryService.getAllBooks().collect { bookList ->
                    _books.value = bookList
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addBook(filePath: String) {
        viewModelScope.launch {
            try {
                libraryService.addBook(filePath)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun scanForBooks(fileScanner: com.calibre.android.util.FileScanner) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val files = fileScanner.scanForBooks()
                files.forEach { file ->
                    try {
                        libraryService.addBook(file.absolutePath)
                    } catch (e: Exception) {
                        // Skip individual errors
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
