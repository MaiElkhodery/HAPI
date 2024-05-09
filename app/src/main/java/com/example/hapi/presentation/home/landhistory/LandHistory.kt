package com.example.hapi.presentation.home.landhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.hapi.presentation.home.common.LastLandActionContent
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.LandAction
import com.example.hapi.util.LandHistoryFilter
import com.example.hapi.util.Tab
import com.example.hapi.util.isNetworkConnected

@Composable
fun LandHistory(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    landHistoryViewmodel: LandHistoryViewModel = hiltViewModel()
) {
    var isNetworkConnected by remember {
        mutableStateOf(true)
    }
    val actionType = landHistoryViewmodel.actionType.collectAsState().value
    LaunchedEffect(key1 = actionType) {
        isNetworkConnected = isNetworkConnected()
        if (actionType.isBlank())
            landHistoryViewmodel.getAllLandHistory()
        else
            landHistoryViewmodel.getLandHistoryByActionType()
    }

    val landHistoryList = landHistoryViewmodel.landHistory.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 28.dp)
    ) {
        val (header, boxes, list) = createRefs()
        val topGuideLine = createGuidelineFromTop(.02f)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(header) {
                    top.linkTo(topGuideLine)
                },
            imageId = R.drawable.back_home,
            topText = stringResource(id = R.string.detection),
            downText = stringResource(id = R.string.history)
        ) {
            mainViewModel.setSelectedTab(Tab.HOME)
            navController.popBackStack()
        }

        LandHistoryFilters(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(boxes) {
                    top.linkTo(header.bottom, margin = 16.dp)
                },
            onAllActionsSelected = { landHistoryViewmodel.modifyActionType("") },
            onFertilizationSelected = { landHistoryViewmodel.modifyActionType(LandHistoryFilter.FERTILIZATION.name.lowercase()) },
            onIrrigationSelected = { landHistoryViewmodel.modifyActionType(LandHistoryFilter.IRRIGATION.name.lowercase()) }
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(list) {
                    top.linkTo(boxes.bottom, margin = 22.dp)
                }
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(landHistoryList.size) { index ->
                    val landData = landHistoryList[index]
                    LastLandActionContent(
                        modifier = Modifier.padding(vertical = 11.dp),
                        action = LandAction.valueOf(landData.action_type.uppercase()),
                        date = landData.date,
                        time = landData.time
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun DetectionHistoryPreview() {
    LandHistory(rememberNavController())
}