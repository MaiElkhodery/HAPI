package com.example.hapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hapi.presentation.auth.signin.signinRoute
import com.example.hapi.presentation.auth.signup.farmersignup.farmerSignupRoute
import com.example.hapi.presentation.auth.signup.landownersignup.cropselection.signupCropSelectionRoute
import com.example.hapi.presentation.auth.signup.landownersignup.finalcrop.finalSelectedCropRoute
import com.example.hapi.presentation.auth.signup.landownersignup.recommendedcrops.recommendedCropsRoute
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.cropSelectionStrategyRoute
import com.example.hapi.presentation.auth.signup.landownersignup.signup.landownerSignupRoute
import com.example.hapi.presentation.detection.cropselection.cropSelectionRoute
import com.example.hapi.presentation.detection.guest_cropselection.guestCropSelectionRoute
import com.example.hapi.presentation.detection.imageselection.imageCaptureRoute
import com.example.hapi.presentation.home.detectiondetails.ui.detectionDetailsRoute
import com.example.hapi.presentation.home.detectionhistory.ui.detectionHistoryRoute
import com.example.hapi.presentation.home.landhistory.ui.landHistoryRoute
import com.example.hapi.presentation.language_setup.languageSetUpRoute
import com.example.hapi.presentation.main.mainRoute
import com.example.hapi.presentation.progress.progressRoute
import com.example.hapi.presentation.role_selection.roleSelectionRoute
import com.example.hapi.presentation.settings.about.aboutUsRoute
import com.example.hapi.presentation.settings.data_and_storage.ui.dataAndStorageRoute
import com.example.hapi.presentation.settings.farmers.ui.farmersRoute
import com.example.hapi.presentation.settings.general.ui.settingsRoute
import com.example.hapi.presentation.settings.language.languageSettingsRoute
import com.example.hapi.presentation.settings.support.helpAndSupportRoute
import com.example.hapi.presentation.splash.ui.splashOneRoute
import com.example.hapi.presentation.welcome.welcomeRoute

@Composable
fun NavGraph(navController: NavHostController, onLanguageSelected: (String) -> Unit) {
    val startRoute = "splash"
    NavHost(navController = navController, startDestination = startRoute) {
        progressRoute(navController)
        splashOneRoute(navController)
        progressRoute(navController)
        roleSelectionRoute(navController)
        farmerSignupRoute(navController)
        landownerSignupRoute(navController)
        cropSelectionStrategyRoute(navController)
        signupCropSelectionRoute(navController)
        recommendedCropsRoute(navController)
        finalSelectedCropRoute(navController)
        signinRoute(navController)
        welcomeRoute(navController)
        detectionHistoryRoute(navController)
        detectionDetailsRoute(navController)
        guestCropSelectionRoute(navController)
        imageCaptureRoute(navController)
        landHistoryRoute(navController)
        mainRoute(navController)
        settingsRoute(navController)
        farmersRoute(navController)
        dataAndStorageRoute(navController)
        helpAndSupportRoute(navController)
        aboutUsRoute(navController)
        cropSelectionRoute(navController)
        languageSettingsRoute(navController, onLanguageSelected)
        languageSetUpRoute(navController, onLanguageSelected)
    }
}