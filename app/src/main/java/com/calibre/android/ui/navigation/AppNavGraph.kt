package com.calibre.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.calibre.android.ui.screen.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Library.route
    ) {
        composable(NavDestination.Library.route) {
            LibraryScreen(
                onNavigateToSettings = { navController.navigate(NavDestination.Settings.route) },
                onNavigateToBookDetail = { bookId -> 
                    navController.navigate(NavDestination.BookDetail.createRoute(bookId)) 
                }
            )
        }
        composable(NavDestination.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = NavDestination.BookDetail.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookDetailScreen(
                bookId = bookId,
                onNavigateBack = { navController.popBackStack() },
                onReadEPUB = { navController.navigate(NavDestination.BookViewerEPUB.createRoute(bookId)) },
                onReadPDF = { navController.navigate(NavDestination.BookViewerPDF.createRoute(bookId)) }
            )
        }
        composable(
            route = NavDestination.BookViewerEPUB.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookViewerEPUBScreen(bookId = bookId, onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = NavDestination.BookViewerPDF.route,
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookViewerPDFScreen(bookId = bookId, onNavigateBack = { navController.popBackStack() })
        }
    }
}
