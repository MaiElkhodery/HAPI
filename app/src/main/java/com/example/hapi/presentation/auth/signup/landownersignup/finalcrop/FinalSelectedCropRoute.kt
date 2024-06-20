package com.example.hapi.presentation.auth.signup.landownersignup.finalcrop

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "final_crop/{cropId}"
fun NavGraphBuilder.finalSelectedCropRoute(navController: NavController) {
    composable(route = ROUTE) { backEntryStack ->
        FinalSelectedCrop(
            navController = navController, cropId = backEntryStack.arguments?.getString("cropId")!!.toInt()
        )
    }
}

fun NavController.navigateToFinalSelectedCrop(crop: Int) {
    val route = "final_crop/$crop"
    navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}