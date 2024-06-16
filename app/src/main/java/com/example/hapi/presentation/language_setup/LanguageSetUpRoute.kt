package com.example.hapi.presentation.language_setup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "language_setup"
fun NavGraphBuilder.languageSetUpRoute(
    navController: NavController,
    onLanguageSelected: (String) -> Unit
) {

    composable(route = ROUTE) {
        LanguageSetUp(navController = navController, onLanguageSelected = onLanguageSelected)
    }
}

fun NavController.navigateToLanguageSetUp() {
    navigate(ROUTE){
        popUpTo(0){
            inclusive = true
        }
    }
}