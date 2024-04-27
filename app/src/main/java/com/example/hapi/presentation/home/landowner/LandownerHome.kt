package com.example.hapi.presentation.home.landowner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.home.common.CustomNavigationBottom
import com.example.hapi.presentation.home.common.CustomNavigationBottomBackground
import com.example.hapi.presentation.home.common.HistoryCard
import com.example.hapi.presentation.home.common.HomeLandData
import com.example.hapi.presentation.home.cropselection.navigateToCropSelection
import com.example.hapi.presentation.home.detectiondetails.navigateToDetectionDetails
import com.example.hapi.presentation.home.detectionhistory.navigateToDetectionHistory
import com.example.hapi.presentation.home.landhistory.navigateToLandHistory
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
    LaunchedEffect(true) {
        isNetworkConnected = isNetworkConnected()
    }

    val username = viewModel.username.collectAsState().value
    val detectionDate = viewModel.detectionDate.collectAsState().value
    val detectionTime = viewModel.detectionTime.collectAsState().value
    val detectionRemoteId = viewModel.detectionRemoteId.collectAsState().value
    val imageUrl = viewModel.imageUrl.collectAsState().value
    val landActionType = viewModel.landActionType.collectAsState().value
    val landActionDate = viewModel.landActionDate.collectAsState().value
    val landActionTime = viewModel.landActionTime.collectAsState().value
    val isLoading = viewModel.loading.collectAsState().value
    val waterLevel = viewModel.waterLevel.collectAsState().value
    val npk = viewModel.npk.collectAsState().value
    val crop = viewModel.crop.collectAsState().value
    val lastFarmerUsername = viewModel.lastFarmerUsername.collectAsState().value
    val lastFarmerDate = viewModel.lastFarmerDate.collectAsState().value
    val lastFarmerTime = viewModel.lastFarmerTime.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (welcomeHeader, dataHeader, content, historyCards, navBottom, bottomBackground) = createRefs()
        val topGuideLine = createGuidelineFromTop(.08f)


        NavHeader(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(welcomeHeader) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(dataHeader.top)
                },
            imageId = R.drawable.logo,
            topText = stringResource(id = R.string.welcome),
            downText = username,
        )
        LandData(
            modifier = Modifier.constrainAs(dataHeader) {
                top.linkTo(welcomeHeader.bottom, margin = 21.dp)
                bottom.linkTo(content.top)
            },
            crop = crop,
            waterLevel = waterLevel,
            npk = npk
        )

        HomeLandData(
            modifier = Modifier
                .padding(horizontal = 35.dp)
                .constrainAs(content) {
                    top.linkTo(dataHeader.bottom, margin = 21.dp)
                    bottom.linkTo(historyCards.top)
                },
            lastLandAction = com.example.hapi.domain.model.LandAction(
                name = landActionType.uppercase(),
                date = landActionDate,
                time = landActionTime
            ),
            detectionUsername = username,
            detectionDate = detectionDate,
            detectionTime = detectionTime,
            imageUrl = if (isNetworkConnected) imageUrl else "",
            lastFarmerDate = lastFarmerDate,
            lastFarmerTime = lastFarmerTime,
            lastFarmerUsername = lastFarmerUsername,
        ) {
            navController.navigateToDetectionDetails(
                id = detectionRemoteId.toString()
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .constrainAs(historyCards) {
                    top.linkTo(content.bottom, margin = 12.dp)
                    bottom.linkTo(navBottom.top, margin = 33.dp)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HistoryCard(
                type = "land",
                modifier = Modifier.weight(1f)

            ) {
                navController.navigateToLandHistory()
            }
            HistoryCard(
                type = "detection",
                modifier = Modifier.weight(1f)

            ) {
                navController.navigateToDetectionHistory()
            }
        }

        CustomNavigationBottomBackground(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomBackground) {
                    bottom.linkTo(parent.bottom)
                }
        )
        CustomNavigationBottom(
            modifier = Modifier
                .padding(top = 12.dp)
                .constrainAs(navBottom) {
                    bottom.linkTo(parent.bottom)
                },
            onHomeClick = {
                navController.navigateToLandownerHome()
            },
            onCameraClick = {
                navController.navigateToCropSelection()
            },
            onSettingsClick = {

            }
        )
    }
}


@Preview
@Composable
fun LandownerHomePreview() {
    LandownerHome(
        rememberNavController()
    )
}