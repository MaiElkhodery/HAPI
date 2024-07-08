package com.example.hapi.presentation.home.landowner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.domain.model.LandAction
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.detectiondetails.ui.navigateToDetectionDetails
import com.example.hapi.presentation.home.detectionhistory.ui.navigateToDetectionHistory
import com.example.hapi.presentation.home.landhistory.ui.navigateToLandHistory
import com.example.hapi.presentation.home.landowner.viewmodel.LandownerHomeViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.isNetworkConnected

@Composable
fun LandownerHome(
    navController: NavController,
    viewModel: LandownerHomeViewModel = hiltViewModel()
) {
    var isNetworkConnected by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        isNetworkConnected = isNetworkConnected()
        if (isNetworkConnected()) {
            viewModel.fetchDetectionHistory()
            viewModel.fetchLandHistory()
            viewModel.fetchTanksData()
            viewModel.getRemoteLastFarmer()
        }
        viewModel.getLastDetection()
        viewModel.getLastLandHistoryItem()
        viewModel.getTanksData()
    }

    val username = viewModel.username.collectAsState().value
    val detectionDate = viewModel.detectionDate.collectAsState().value
    val detectionTime = viewModel.detectionTime.collectAsState().value
    val detectionRemoteId = viewModel.detectionRemoteId.collectAsState().value
    val detectionUsername = viewModel.detectionUsername.collectAsState().value
    val imageUrl = viewModel.imageUrl.collectAsState().value
    val landActionType = viewModel.landActionType.collectAsState().value
    val landActionDate = viewModel.landActionDate.collectAsState().value
    val landActionTime = viewModel.landActionTime.collectAsState().value
    val waterLevel = viewModel.waterLevel.collectAsState().value
    val crop = viewModel.crop.collectAsState().value
    val lastFarmerUsername = viewModel.lastFarmerUsername.collectAsState().value
    val lastFarmerDate = viewModel.lastFarmerDate.collectAsState().value
    val lastFarmerTime = viewModel.lastFarmerTime.collectAsState().value
    val nitrogen = viewModel.nitrogen.collectAsState().value
    val phosphorus = viewModel.phosphorus.collectAsState().value
    val potassium = viewModel.potassium.collectAsState().value

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val maxHeight = maxHeight
        val maxWidth = maxWidth

        val largeHorizontalPadding = maxWidth * 0.1f
        val smallHorizontalPadding = maxWidth * 0.01f
        val backIconSize = if (maxHeight < 650.dp) 60 else 75
        val verticalPadding = maxHeight * .03f

        val headerFontSize = when {
            maxWidth < 360.dp -> 12
            maxWidth in 360.dp..400.dp -> 15
            else -> 17
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            NavHeader(
                modifier = Modifier
                    .padding(horizontal = smallHorizontalPadding,vertical = verticalPadding),
                imageId = R.drawable.logo,
                topText = stringResource(id = R.string.welcome_home),
                downText = username,
                imageSize = backIconSize,
                fontSize = headerFontSize
            )

            TanksData(
                modifier = Modifier.padding(horizontal = smallHorizontalPadding),
                crop = crop,
                waterTankLevel = if (waterLevel.isNotBlank()) waterLevel.toInt() else 0,
                nTankLevel = if (nitrogen.isNotBlank()) nitrogen.toInt() else 0,
                pTankLevel = if (phosphorus.isNotBlank()) phosphorus.toInt() else 0,
                kTankLevel = if (potassium.isNotBlank()) potassium.toInt() else 0
            )
            LandownerHomeLandData(
                modifier = Modifier
                    .padding(horizontal = largeHorizontalPadding,vertical = verticalPadding),
                width = maxWidth,
                height = maxHeight,
                lastLandAction = LandAction(
                    name = landActionType.uppercase(),
                    date = landActionDate,
                    time = landActionTime
                ),
                detectionUsername = detectionUsername,
                detectionDate = detectionDate,
                detectionTime = detectionTime,
                imageUrl = if (isNetworkConnected) imageUrl else "",
                lastFarmerDate = lastFarmerDate,
                lastFarmerTime = lastFarmerTime,
                lastFarmerUsername = lastFarmerUsername,
                onClickDetectionHistory = { navController.navigateToDetectionHistory() },
                onClickLandHistory = { navController.navigateToLandHistory() },
                onClickDetailsIcon = {
                    navController.navigateToDetectionDetails(
                        id = detectionRemoteId.toString()
                    )
                }
            )
            Spacer(modifier = Modifier.height(maxHeight* 0.05f))
        }
    }
}


@Preview
@Composable
fun LandownerHomePreview() {
    LandownerHome(
        rememberNavController()
    )
}