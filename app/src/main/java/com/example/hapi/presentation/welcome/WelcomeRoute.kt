package com.example.hapi.presentation.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "welcome"
fun NavGraphBuilder.welcomeRoute(navController: NavController) {

    composable(route = ROUTE) {
        WelcomeScreen(navController)
    }
}
fun NavController.navigateToWelcomeScreen(){
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = true
        }
    }
}