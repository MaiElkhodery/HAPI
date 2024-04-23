package com.example.hapi.presentation.home.landhistory

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "land_history"
fun NavGraphBuilder.landHistoryRoute(navController: NavController) {

    composable(route = ROUTE) {
        LandHistory(navController = navController)
    }
}

fun NavController.navigateToLandHistory() {
    navigate(ROUTE)
}