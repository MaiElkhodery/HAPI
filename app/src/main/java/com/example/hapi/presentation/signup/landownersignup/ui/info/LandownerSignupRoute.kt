package com.example.hapi.presentation.signup.landownersignup.ui.info

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "landowner_signup"
fun NavGraphBuilder.landownerSignupRoute(navController: NavController) {
    composable(route = ROUTE) {
        LandownerSignup(navController)
    }
}

fun NavController.navToLandownerSignup() {
    navigate(ROUTE)
}