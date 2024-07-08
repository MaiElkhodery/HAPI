package com.example.hapi.presentation.progress

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "progress/{final}/{isFarmer}"
fun NavGraphBuilder.progressRoute(navController: NavController) {

    composable(route = ROUTE) { backStackEntry ->
        val final = backStackEntry.arguments?.getString("final") ?: "false"
        val isFarmer = backStackEntry.arguments?.getString("isFarmer") ?: "false"
        ProgressScreen(navController, final = final, isFarmer = isFarmer.toBoolean())
    }
}

fun NavController.navToProgress(
    final: String = "false",
    isFarmer: String = "false"
) {
    val route = "progress/$final/$isFarmer"
    navigate(route) {
        popUpTo(ROUTE) {
            inclusive = true
        }
    }
}