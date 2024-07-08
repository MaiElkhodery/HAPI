package com.example.hapi.presentation.auth.signup.landownersignup.recommendedcrops

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "recommended_crops/{crops}"
fun NavGraphBuilder.recommendedCropsRoute(navController: NavController) {
    composable(route = ROUTE) {
        val cropsString = it.arguments?.getString("crops")
        RecommendedCrops(
            navController = navController,
            crops = cropsString!!
        )
    }
}

fun NavController.navigateToRecommendedCrops(
    crops: String
) {
    val route = "recommended_crops/$crops"
    navigate(route)
}