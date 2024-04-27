package com.example.hapi.presentation.home.landowner

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "landowner_home"
fun NavGraphBuilder.landownerHomeRoute(navController: NavController) {

    composable(route = ROUTE) {
        LandownerHome(navController)
    }
}
fun NavController.navigateToLandownerHome(){
    navigate(ROUTE){
//        popBackStack()
        popUpTo(ROUTE){
            inclusive = false
        }
    }
}