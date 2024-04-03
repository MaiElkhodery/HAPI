package com.example.hapi.presentation.auth.signup.landownersignup.finalcrop

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "final_crop/{crop}"
fun NavGraphBuilder.finalCropRoute(navController: NavController) {
    composable(route = ROUTE) { backEntryStack ->
        FinalCropScreen(
            navController = navController, crop = backEntryStack.arguments?.getString("crop")!!
        )
    }
}

fun NavController.navigateToFinalCrop(crop: String) {
    val route = "final_crop/$crop"
    navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}