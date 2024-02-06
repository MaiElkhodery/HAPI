package com.example.hapi.presentation.signup.progress

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "progress"
fun NavGraphBuilder.progressRoute(navController: NavController) {

    composable(route = ROUTE) {
        ProgressScreen(navController)
    }
}
fun NavController.navigateToProgress(){
    navigate(ROUTE)
}