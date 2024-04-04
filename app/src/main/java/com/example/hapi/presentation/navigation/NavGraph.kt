package com.example.hapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hapi.presentation.auth.signin.signinRoute
import com.example.hapi.presentation.auth.signup.farmersignup.farmerSignupRoute
import com.example.hapi.presentation.auth.signup.landownersignup.choose.cropChooseRoute
import com.example.hapi.presentation.auth.signup.landownersignup.detection.cropDetectionRoute
import com.example.hapi.presentation.auth.signup.landownersignup.finalcrop.finalCropRoute
import com.example.hapi.presentation.auth.signup.landownersignup.info.landownerSignupRoute
import com.example.hapi.presentation.auth.signup.landownersignup.recommendation.cropRecommendationRoute
import com.example.hapi.presentation.home.cropdetection.imageCaptureRoute
import com.example.hapi.presentation.home.cropselection.cropSelectionRoute
import com.example.hapi.presentation.home.detectiondetails.detectionDetailsRoute
import com.example.hapi.presentation.home.detectionhistory.detectionHistoryRoute
import com.example.hapi.presentation.home.landowner.landownerHomeRoute
import com.example.hapi.presentation.identityselection.identitySelectionRoute
import com.example.hapi.presentation.progress.progressRoute
import com.example.hapi.presentation.main.mainRoute
import com.example.hapi.presentation.splash.splashOneRoute

@Composable
fun NavGraph(navController: NavHostController) {
    val startRoute = "splash"
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
        mainRoute(navController)
        landownerHomeRoute(navController)
        detectionHistoryRoute(navController)
        detectionDetailsRoute(navController)
        cropSelectionRoute(navController)
        imageCaptureRoute(navController)
    }
}