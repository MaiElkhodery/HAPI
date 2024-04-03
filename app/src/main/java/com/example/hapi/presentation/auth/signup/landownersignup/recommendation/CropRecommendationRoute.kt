package com.example.hapi.presentation.auth.signup.landownersignup.recommendation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "crop_recommendation"
fun NavGraphBuilder.cropRecommendationRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropRecommendationScreen(
            navController = navController
        )
    }
}

fun NavController.navigateToCropRecommendation() {
    navigate(ROUTE)
}