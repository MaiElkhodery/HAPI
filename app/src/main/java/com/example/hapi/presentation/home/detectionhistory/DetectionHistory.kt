package com.example.hapi.presentation.home.detectionhistory

import android.util.Log
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
import com.example.hapi.data.local.room.entities.history.DetectionHistoryWithDiseases
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.home.common.DetectionHistoryCard
import com.example.hapi.presentation.home.common.LoadingBox
import com.example.hapi.presentation.home.common.RoundedYellowBoxes
import com.example.hapi.presentation.home.detectiondetails.navigateToDetectionDetails
import com.example.hapi.presentation.home.landowner.navigateToLandownerHome
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.isNetworkConnected

@Composable
fun DetectionHistory(
    navController: NavController,
    viewmodel: DetectionHistoryViewModel = hiltViewModel()
) {
    var isNetworkConnected by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        isNetworkConnected = isNetworkConnected()
        if (isNetworkConnected) {
            viewmodel.getDetectionHistory()
            viewmodel.getAndSaveLastFiveDetections()
        } else {
            viewmodel.getLocalDetections()
        }
    }

    val detectionHistoryList =
        if (isNetworkConnected) viewmodel.detectionList.collectAsState().value
        else
            viewmodel.localDetections.collectAsState().value

    val isLoading = viewmodel.loading.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp)
    ) {
        val (header, boxes, list) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(.01f)
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
            navController.navigateToLandownerHome()
        }

        RoundedYellowBoxes(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(boxes) {
                    top.linkTo(header.bottom, margin = 16.dp)
//                    bottom.linkTo(list.top)
                }
        )
        if (isLoading) {
            LoadingBox(modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(list) {
                    top.linkTo(boxes.bottom, margin = 22.dp)
//                    bottom.linkTo(bottomGuideLine)
                    centerVerticallyTo(parent)
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(horizontal = 26.dp)
                    .constrainAs(list) {
                        top.linkTo(boxes.bottom, margin = 22.dp)
//                        bottom.linkTo(bottomGuideLine)
                    }
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(detectionHistoryList.size) { index ->
                        if (isNetworkConnected) {
                            val detectionResult =
                                detectionHistoryList[index] as DetectionHistoryResponse
                            Log.d("DetectionHistory", "DetectionHistory: $detectionResult")
                            DetectionHistoryCard(
                                modifier = Modifier.padding(vertical = 8.dp),
                                username = detectionResult.username,
                                date = detectionResult.date,
                                time = detectionResult.time,
                                imagePath = detectionResult.image_url,
                            ) {
                                navController.navigateToDetectionDetails(detectionResult.id.toString())
                            }
                        } else {
                            val detectionResult =
                                detectionHistoryList[index] as DetectionHistoryWithDiseases
                            Log.d("DetectionHistory", "DetectionHistory: $detectionResult")
                            DetectionHistoryCard(
                                modifier = Modifier.padding(vertical = 8.dp),
                                username = detectionResult.detection.name,
                                date = detectionResult.detection.date,
                                time = detectionResult.detection.time,
                                byteArray = detectionResult.detection.imageByteArray,
                                imagePath = ""
                            ) {
                                navController.navigateToDetectionDetails(detectionResult.detection.detectionId.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetectionHistoryPreview() {
    DetectionHistory(rememberNavController())
}