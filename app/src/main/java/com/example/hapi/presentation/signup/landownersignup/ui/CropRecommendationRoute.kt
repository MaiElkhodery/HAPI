package com.example.hapi.presentation.signup.landownersignup.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "crop_recommendation"
fun NavGraphBuilder.cropRecommendationRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropRecommendation(navController)
    }
}

fun NavController.navToCropRecommendation() {
    navigate(ROUTE)
}