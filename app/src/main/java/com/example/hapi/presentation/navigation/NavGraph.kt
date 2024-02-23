package com.example.hapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hapi.presentation.signin.signinRoute
import com.example.hapi.presentation.signup.farmersignup.ui.farmerSignupRoute
import com.example.hapi.presentation.signup.landownersignup.choose.cropChooseRoute
import com.example.hapi.presentation.signup.landownersignup.detection.cropDetectionRoute
import com.example.hapi.presentation.signup.landownersignup.finalcrop.finalCropRoute
import com.example.hapi.presentation.signup.landownersignup.info.landownerSignupRoute
import com.example.hapi.presentation.signup.landownersignup.recommendation.cropRecommendationRoute
import com.example.hapi.presentation.identityselection.identitySelectionRoute
import com.example.hapi.presentation.progress.progressRoute
import com.example.hapi.presentation.splash.splashOneRoute

@Composable
fun NavGraph(navController: NavHostController) {
//    val startRoute = "progress/{final}"
    val startRoute = "signin"
    NavHost(navController = navController, startDestination = startRoute) {
        progressRoute(navController)
        splashOneRoute(navController)
        progressRoute(navController)
        identitySelectionRoute(navController)
        farmerSignupRoute(navController)
        landownerSignupRoute(navController)
        cropDetectionRoute(navController)
        cropChooseRoute(navController)
        cropRecommendationRoute(navController)
        finalCropRoute(navController)
        signinRoute(navController)
    }
}