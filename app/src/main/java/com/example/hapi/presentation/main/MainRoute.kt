package com.example.hapi.presentation.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "main"
fun NavGraphBuilder.mainRoute(navController: NavController) {

    composable(route = ROUTE) {
        Main(navController)
    }
}
fun NavController.navigateToMain(){
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = true
        }
    }
}