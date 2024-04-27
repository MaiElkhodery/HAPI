package com.example.hapi.presentation.home.farmer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "farmer_home"
fun NavGraphBuilder.farmerHomeRoute(navController: NavController) {

    composable(route = ROUTE) {
        FarmerHome(navController)
    }
}
fun NavController.navigateToFarmerHome(){
    navigate(ROUTE){
//        popBackStack()
        popUpTo(ROUTE){
            inclusive = false
        }
    }
}