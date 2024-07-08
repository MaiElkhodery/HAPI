package com.example.hapi.presentation.settings.support

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "help_and_support"
fun NavGraphBuilder.helpAndSupportRoute(navController: NavController) {

    composable(route = ROUTE) {
        HelpAndSupport(navController = navController)
    }
}

fun NavController.navigateToHelpAndSupport() {
    navigate(ROUTE)
}