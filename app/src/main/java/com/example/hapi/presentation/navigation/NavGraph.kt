package com.example.hapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hapi.presentation.auth.signin.signinRoute
import com.example.hapi.presentation.auth.signup.farmersignup.farmerSignupRoute
import com.example.hapi.presentation.auth.signup.landownersignup.cropselection.signupCropSelectionRoute
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.cropSelectionStrategyRoute
import com.example.hapi.presentation.auth.signup.landownersignup.finalcrop.FinalSelectedCropRoute
import com.example.hapi.presentation.auth.signup.landownersignup.recommendedcrops.recommendedCropsRoute
import com.example.hapi.presentation.auth.signup.landownersignup.signup.landownerSignupRoute
import com.example.hapi.presentation.detection.cropselection.cropSelectionRoute
import com.example.hapi.presentation.detection.guest_cropselection.guestCropSelectionRoute
import com.example.hapi.presentation.home.detectiondetails.detectionDetailsRoute
import com.example.hapi.presentation.home.detectionhistory.detectionHistoryRoute
import com.example.hapi.presentation.detection.imageselection.imageCaptureRoute
import com.example.hapi.presentation.home.farmer.farmerHomeRoute
import com.example.hapi.presentation.home.landhistory.landHistoryRoute
import com.example.hapi.presentation.home.landowner.landownerHomeRoute
import com.example.hapi.presentation.identityselection.identitySelectionRoute
import com.example.hapi.presentation.main.mainRoute
import com.example.hapi.presentation.welcome.welcomeRoute
import com.example.hapi.presentation.progress.progressRoute
import com.example.hapi.presentation.settings.about.aboutUsRoute
import com.example.hapi.presentation.settings.data.dataAndStorageRoute
import com.example.hapi.presentation.settings.farmer.farmerSettingsRoute
import com.example.hapi.presentation.settings.landfarmers.farmersRoute
import com.example.hapi.presentation.settings.landowner.landownerSettingsRoute
import com.example.hapi.presentation.settings.language.languageSettingsRoute
import com.example.hapi.presentation.settings.support.helpAndSupportRoute
import com.example.hapi.presentation.splash.splashOneRoute

@Composable
fun NavGraph(navController: NavHostController) {
    val startRoute = "splash"
//    val startRoute = "landowner_home"
    NavHost(navController = navController, startDestination = startRoute) {
        progressRoute(navController)
        splashOneRoute(navController)
        progressRoute(navController)
        identitySelectionRoute(navController)
        farmerSignupRoute(navController)
        landownerSignupRoute(navController)
        cropSelectionStrategyRoute(navController)
        signupCropSelectionRoute(navController)
        recommendedCropsRoute(navController)
        FinalSelectedCropRoute(navController)
        signinRoute(navController)
        welcomeRoute(navController)
        landownerHomeRoute(navController)
        farmerHomeRoute(navController)
        detectionHistoryRoute(navController)
        detectionDetailsRoute(navController)
        guestCropSelectionRoute(navController)
        imageCaptureRoute(navController)
        landHistoryRoute(navController)
        mainRoute(navController)
        landownerSettingsRoute(navController)
        farmersRoute(navController)
        dataAndStorageRoute(navController)
        helpAndSupportRoute(navController)
        aboutUsRoute(navController)
        farmerSettingsRoute(navController)
        cropSelectionRoute(navController)
        languageSettingsRoute(navController)
    }
}