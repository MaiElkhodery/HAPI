package com.example.hapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hapi.presentation.signup.farmersignup.ui.farmerSignupRoute
import com.example.hapi.presentation.signup.landownersignup.ui.cropdetection.cropChooseRoute
import com.example.hapi.presentation.signup.landownersignup.ui.cropdetection.cropDetectionRoute
import com.example.hapi.presentation.signup.landownersignup.ui.cropdetection.finalCropRoute
import com.example.hapi.presentation.signup.landownersignup.ui.info.landownerSignupRoute
import com.example.hapi.presentation.signup.progress.progressRoute
import com.example.hapi.presentation.splash.splashOneRoute

@Composable
fun NavGraph(navController: NavHostController) {
    val startRoute = "splash"
    NavHost(navController = navController, startDestination = startRoute) {
        splashOneRoute(navController)
        progressRoute(navController)
        farmerSignupRoute(navController)
        landownerSignupRoute(navController)
        cropDetectionRoute(navController)
        cropChooseRoute(navController)
        finalCropRoute(navController)
    }
}