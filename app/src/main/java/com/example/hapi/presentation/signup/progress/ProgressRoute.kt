package com.example.hapi.presentation.signup.progress

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "progress/{isFinal}"
fun NavGraphBuilder.progressRoute(navController: NavController) {

    composable(route = ROUTE) {backStackEntry->
        val isFinal = backStackEntry.arguments?.getBoolean("isFinal") ?: false
        ProgressScreen(navController, isFinal)
    }
}
fun NavController.navigateToProgress(
    isFinal:Boolean = false
){
    val route = "progress/$isFinal"
    navigate(route)
}