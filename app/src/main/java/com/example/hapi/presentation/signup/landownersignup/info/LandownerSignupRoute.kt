package com.example.hapi.presentation.signup.landownersignup.info

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "landowner_signup"
fun NavGraphBuilder.landownerSignupRoute(navController: NavController) {
    composable(route = ROUTE) {
        Log.d("NAVIGATION","display screen")
        LandownerSignup(navController)
    }
}

fun NavController.navToLandownerSignup() {
    navigate(ROUTE)
}