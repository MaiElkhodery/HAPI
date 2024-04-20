package com.example.hapi.presentation.home.landowner

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.home.common.CustomNavigationBottom
import com.example.hapi.presentation.home.common.CustomNavigationBottomBackground
import com.example.hapi.presentation.home.common.HistoryCard
import com.example.hapi.presentation.home.common.HomeHeader
import com.example.hapi.presentation.home.common.HomeLandData
import com.example.hapi.presentation.home.cropselection.navigateToCropSelection
import com.example.hapi.presentation.home.detectiondetails.navigateToDetectionDetails
import com.example.hapi.presentation.home.detectionhistory.navigateToDetectionHistory
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.LandAction
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
        Log.d("LANDOWNER HOME", "isNetworkConnected: $isNetworkConnected")
        if (isNetworkConnected) {
            viewModel.getDetectionHistory()
        } else {
            viewModel.getLastDetectionAndSetLastId()
        }
    }

    val username = viewModel.username.collectAsState().value
    val date = viewModel.date.collectAsState().value
    val time = viewModel.time.collectAsState().value
    val remoteId = viewModel.remoteId.collectAsState().value
    val imageUrl = viewModel.imageUrl.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (header, content, historyCards, navBottom, bottomBackground) = createRefs()
        val topGuideLine = createGuidelineFromTop(.07f)

        HomeHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(content.top)
                },
            imageId = R.drawable.user_img,
            username = "khaled"
        )
        HomeLandData(
            modifier = Modifier
                .padding(horizontal = 35.dp)
                .constrainAs(content) {
                    top.linkTo(header.bottom, margin = 21.dp)
                    bottom.linkTo(historyCards.top)
                },
            lastLandAction = com.example.hapi.domain.model.LandAction(
                LandAction.FERTILIZATION.name,
                "2021-09-01",
                "12:00"
            ),
            username = username,
            date = date,
            time = time,
            imageUrl = if (isNetworkConnected) imageUrl else ""
        ) {
            navController.navigateToDetectionDetails(
                id = remoteId.toString()
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