package com.example.hapi.presentation.role_selection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "role_selection"
fun NavGraphBuilder.roleSelectionRoute(navController: NavController) {

    composable(route = ROUTE) {
        RoleOptions(navController = navController)
    }
}

fun NavController.navigateToRoleSelection(
) {
    navigate(ROUTE) {
        popUpTo(ROUTE) {
            inclusive = true
        }
    }
}