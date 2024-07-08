package com.example.hapi.presentation.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "main/{role}"
fun NavGraphBuilder.mainRoute(navController: NavController) {

    composable(route = ROUTE) {
        val role = it.arguments?.getString("role")
        HapiMainScreen(navController = navController, role = role!!)
    }
}

fun NavController.navigateToMainScreen(
    role: String = ""
) {
    val route = "main/$role"
    navigate(route){
        popUpTo(0){
            inclusive = true
        }
    }
}