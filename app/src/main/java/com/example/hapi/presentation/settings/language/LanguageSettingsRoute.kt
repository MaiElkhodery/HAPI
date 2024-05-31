package com.example.hapi.presentation.settings.language

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "language_settings"
fun NavGraphBuilder.languageSettingsRoute(navController: NavController) {

    composable(route = ROUTE) {
        LanguageSettings(navController = navController)
    }
}

fun NavController.navigateToLanguageSettings() {
    navigate(ROUTE)
}