package com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "crop_selection_strategy"
fun NavGraphBuilder.cropSelectionStrategyRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropSelectionStrategy(navController)
    }
}

fun NavController.navigateToCropSelectionStrategy() {
    navigate(ROUTE) {
        popUpTo(ROUTE) {
            inclusive = true
        }
    }
}