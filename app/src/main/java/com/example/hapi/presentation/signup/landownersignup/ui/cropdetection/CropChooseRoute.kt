package com.example.hapi.presentation.signup.landownersignup.ui.cropdetection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "crop_choose"
fun NavGraphBuilder.cropChooseRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropChooseScreen(navController)
    }
}

fun NavController.navToCropChooseScreen() {
    navigate(ROUTE)
}