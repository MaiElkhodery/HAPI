package com.example.hapi.presentation.auth.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "signin"
fun NavGraphBuilder.signinRoute(navController: NavController) {
    composable(route = ROUTE) {
        Signin(navController)
    }
}

fun NavController.navToSignin() {
    navigate( ROUTE)
}