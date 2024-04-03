package com.example.hapi.presentation.home.detectionhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.data.model.Detection
import com.example.hapi.presentation.auth.signup.common.NavHeader
import com.example.hapi.presentation.home.common.DetectionHistoryCard
import com.example.hapi.presentation.home.common.RoundedYellowBoxes
import com.example.hapi.presentation.home.detectiondetails.navigateToDetectionDetails
import com.example.hapi.presentation.home.landowner.navigateToLandownerHome
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun DetectionHistory(navController: NavController) {

    val detectionHistoryList = listOf(
        Detection(
            username = "Ahmed",
            date = "12/12/2021",
            time = "12:00",
            crop = "Wheat",
            possibleDiseases = emptyList(),
            imagePath = ""
        ),
        Detection(
            username = "Ahmed",
            date = "12/12/2021",
            time = "12:00",
            crop = "Wheat",
            possibleDiseases = emptyList(),
            imagePath = ""
        )
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp)
    ) {
        val (header, boxes, list) = createRefs()

        NavHeader(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(header) {
                    top.linkTo(parent.top)
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
                    top.linkTo(header.bottom, margin = Dimens.content_margin)
                }
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(list) {
                    top.linkTo(boxes.bottom, margin = 22.dp)
                }
        ) {
            items(detectionHistoryList.size) { index ->
                val detectionResult = detectionHistoryList[index]
                DetectionHistoryCard(
                    modifier = Modifier.padding(vertical = 8.dp),
                    username = detectionResult.username,
                    date = detectionResult.date,
                    time = detectionResult.time,
                    imagePath = detectionResult.imagePath
                ) {
                    //TODO: modify id
                    navController.navigateToDetectionDetails(0)
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