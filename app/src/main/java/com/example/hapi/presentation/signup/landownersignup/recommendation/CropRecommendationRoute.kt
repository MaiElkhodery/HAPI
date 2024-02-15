package com.example.hapi.presentation.signup.landownersignup.recommendation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hapi.data.model.Crop

private const val ROUTE = "crop_recommendation"
fun NavGraphBuilder.cropRecommendationRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropRecommendationScreen(
            navController = navController
        )
    }
}

fun NavController.navToCropDetection() {
    navigate(ROUTE)
}