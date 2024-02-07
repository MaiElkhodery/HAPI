package com.example.hapi.presentation.signup.farmersignup.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "farmer_signup"
fun NavGraphBuilder.farmerSignupRoute(navController: NavController) {
    composable(route = ROUTE) {
        FarmerSignup(navController)
    }
}

fun NavController.navToFarmerSignup(){
    navigate(ROUTE)
}