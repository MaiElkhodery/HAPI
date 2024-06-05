package com.example.hapi.presentation.identityselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "identity_selection"
fun NavGraphBuilder.identitySelectionRoute(navController: NavController) {

    composable(route = ROUTE) {
        IdentitySelection(navController = navController)
    }
}

fun NavController.navigateToIdentitySelection(
) {
    navigate(ROUTE) {
        popUpTo(ROUTE) {
            inclusive = true
        }
    }
}