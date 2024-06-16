package com.example.hapi.presentation.home.landhistory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.common.HistoryWarning
import com.example.hapi.presentation.home.common.LandActionCard
import com.example.hapi.presentation.home.landhistory.viewmodel.LandHistoryViewModel
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.LandAction
import com.example.hapi.util.LandHistoryFilter
import com.example.hapi.util.Tab
import com.example.hapi.util.isNetworkConnected

@Composable
fun LandHistory(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    landHistoryViewmodel: LandHistoryViewModel = hiltViewModel()
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    var isNetworkConnected by remember {
        mutableStateOf(true)
    }
    val actionType = landHistoryViewmodel.actionType.collectAsState().value

    LaunchedEffect(key1 = actionType) {
        isNetworkConnected = isNetworkConnected()
        if (actionType.isBlank())
            landHistoryViewmodel.getAllLandHistory()
        else
            landHistoryViewmodel.getLandHistoryByActionType(actionType)
    }

    val landHistoryList = landHistoryViewmodel.landHistory.collectAsState().value

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val maxHeight = maxHeight
        val maxWidth = maxWidth

        val headerPadding = if (maxWidth > 300.dp) 22.dp else 16.dp
        val contentHorizontalPadding = if (maxWidth > 300.dp) 32.dp else 22.dp
        val backIconSize = if (maxHeight < 650.dp) 60 else 80
        val verticalPadding = if (maxHeight < 650.dp) 14.dp else 22.dp

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(verticalPadding))

            NavHeader(
                modifier = Modifier.padding(horizontal = headerPadding),
                imageId = if (isEnglish) R.drawable.back_home else R.drawable.home_back_btn_ar,
                topText = stringResource(id = R.string.detection),
                downText = stringResource(id = R.string.history),
                imageSize = backIconSize
            ) {
                mainViewModel.setSelectedTab(Tab.HOME)
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            LandHistoryFilters(
                modifier = Modifier
                    .padding(horizontal = contentHorizontalPadding),
                onFilterSelected = { filterType ->
                    when (filterType) {
                        LandFilterType.All -> landHistoryViewmodel.modifyActionType("")
                        LandFilterType.Fertilization -> landHistoryViewmodel.modifyActionType(
                            LandHistoryFilter.FERTILIZATION.name.lowercase()
                        )

                        LandFilterType.Irrigation -> landHistoryViewmodel.modifyActionType(
                            LandHistoryFilter.IRRIGATION.name.lowercase()
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(verticalPadding))

            if (landHistoryList.isEmpty())
                HistoryWarning(
                    topMsg = R.string.no_actions,
                    downMsg = R.string.wait_for_the_device,
                )
            else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = contentHorizontalPadding)
                ) {
                    items(landHistoryList.size) { index ->
                        val landData = landHistoryList[index]
                        LandActionCard(
                            modifier = Modifier.padding(vertical = 8.dp),
                            action = LandAction.valueOf(landData.action_type.uppercase()),
                            date = landData.date,
                            time = landData.time
                        )
                    }
                }

                Spacer(modifier = Modifier.height(verticalPadding))
            }

        }
    }
}

@Preview
@Composable
fun DetectionHistoryPreview() {
    LandHistory(rememberNavController())
}