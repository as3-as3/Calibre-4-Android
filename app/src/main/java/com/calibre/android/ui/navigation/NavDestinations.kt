package com.calibre.android.ui.navigation

sealed class NavDestination(val route: String) {
    object Library : NavDestination("library")
    object BookDetail : NavDestination("book_detail/{bookId}") {
        fun createRoute(bookId: String) = "book_detail/$bookId"
    }
    object BookViewerEPUB : NavDestination("viewer_epub/{bookId}") {
        fun createRoute(bookId: String) = "viewer_epub/$bookId"
    }
    object BookViewerPDF : NavDestination("viewer_pdf/{bookId}") {
        fun createRoute(bookId: String) = "viewer_pdf/$bookId"
    }
    object Settings : NavDestination("settings")
    object Conversion : NavDestination("conversion/{bookId}") {
        fun createRoute(bookId: String) = "conversion/$bookId"
    }
    object DeviceManagement : NavDestination("devices")
}
