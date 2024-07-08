package com.example.hapi.presentation.auth.signup.landownersignup.cropselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "signup_crop_selection"
fun NavGraphBuilder.signupCropSelectionRoute(navController: NavController) {
    composable(route = ROUTE) {
        SignupCropSelection(navController)
    }
}

fun NavController.navigateToSignupCropSelection() {
    navigate(ROUTE)
}