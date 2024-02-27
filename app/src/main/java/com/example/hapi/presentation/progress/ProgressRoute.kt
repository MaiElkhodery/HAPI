package com.example.hapi.presentation.progress

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "progress/{final}"
fun NavGraphBuilder.progressRoute(navController: NavController) {

    composable(route = ROUTE) { backStackEntry ->
        val final = backStackEntry.arguments?.getString("final") ?: "false"
        ProgressScreen(navController, final)
    }
}

fun NavController.navToProgress(
    final: String = "false"
) {
    val route = "progress/$final"
    navigate(route) {
        popUpTo(ROUTE) {
            inclusive = true
        }
    }
}