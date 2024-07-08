package com.example.hapi.presentation.home.farmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.common.HomeNotFoundWarning
import com.example.hapi.presentation.home.common.FarmerLastDetection
import com.example.hapi.presentation.home.common.HorizontalHistoryCard
import com.example.hapi.presentation.home.detectiondetails.ui.navigateToDetectionDetails
import com.example.hapi.presentation.home.detectionhistory.ui.navigateToDetectionHistory
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.isNetworkConnected

@Composable
fun FarmerHome(
    navController: NavController,
    viewmodel: FarmerHomeViewModel = hiltViewModel()
) {

    val username = viewmodel.username.collectAsState().value
    val detectionDate = viewmodel.detectionDate.collectAsState().value
    val detectionTime = viewmodel.detectionTime.collectAsState().value
    val detectionUsername = viewmodel.detectionUsername.collectAsState().value
    val detectionImageUrl = viewmodel.detectionImageUrl.collectAsState().value
    val detectionRemoteId = viewmodel.detectionRemoteId.collectAsState().value
    val isLoading = viewmodel.loading.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewmodel.getUsername()
        viewmodel.getSavedLastDetection()
        if (isNetworkConnected()) {
            viewmodel.getAndSaveRemoteDetectionHistory()
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (welcomeHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_home)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.nav_header_horizontal_padding)
                .constrainAs(welcomeHeader) {
                    top.linkTo(topGuideLine)
                },
            imageId = R.drawable.logo,
            topText = stringResource(id = R.string.welcome_home),
            downText = username,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp)
                .constrainAs(content) {
                    top.linkTo(welcomeHeader.bottom, margin = 21.dp)
                    centerVerticallyTo(parent)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (detectionDate.isBlank()) {
                NoDetections(
                    warning = stringResource(id = R.string.no_detection),
                    warningDetails = stringResource(id = R.string.no_detection_details)
                )
            } else {
                FarmerLastDetection(
                    username = detectionUsername,
                    date = detectionDate,
                    time = detectionTime,
                    imageUrl = detectionImageUrl
                ) {
                    navController.navigateToDetectionDetails(
                        id = detectionRemoteId
                    )
                }
            }

            HorizontalHistoryCard(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                navController.navigateToDetectionHistory()
            }
        }
    }
}

@Preview
@Composable
fun FarmerHomePreview() {
    FarmerHome(navController = rememberNavController())
}