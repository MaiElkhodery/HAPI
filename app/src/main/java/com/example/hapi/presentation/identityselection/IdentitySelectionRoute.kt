package com.example.hapi.presentation.identityselection

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "identity_selection"
fun NavGraphBuilder.identitySelectionRoute(navController: NavController) {

    composable(route = ROUTE) {
        Log.d("NAVIGATION","nav to selection, composable")
        IdentitySelectionScreen(navController = navController)
    }
}

fun NavController.navigateToIdentitySelection(
) {
    Log.d("NAVIGATION","nav to selection")
    navigate(ROUTE)
}