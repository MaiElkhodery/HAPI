package com.example.hapi.presentation.home.detectiondetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.data.model.Detection
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.home.common.DetectionInfo
import com.example.hapi.presentation.home.common.DetectionLowConfidence
import com.example.hapi.presentation.home.common.DetectionResult
import com.example.hapi.presentation.home.detectionhistory.navigateToDetectionHistory
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop
import com.example.hapi.util.Dimens
import com.example.hapi.util.text.YellowBlackText

@Composable
fun DetectionDetails(
    navController: NavController,
    detectionId: Int
) {
    val detectionResult = Detection(
        username = "Ahmed",
        date = "12/12/2021",
        time = "12:00",
        crop = "WHEAT",
        possibleDiseases = emptyList(),
        imagePath = "",
        isHealthy = true,
        confidence = 90.0f
    )
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        val (header, imageAndData, text, boxes) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.2f)

        NavHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                },
            topText = stringResource(id = R.string.detection),
            downText = stringResource(id = R.string.result)
        ) {
            navController.navigateToDetectionHistory()
        }


        Row(
            modifier = Modifier
                .constrainAs(imageAndData) {
                    top.linkTo(header.bottom, margin = 37.dp)
                    bottom.linkTo(text.top)
                }
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(width = 3.dp, color = YellowAppColor)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .weight(1.5f)

            ) {
                //TODO: change image value
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp),
                    painter = painterResource(id = R.drawable.disease_sample),
                    contentDescription = "crop image",
                    contentScale = ContentScale.FillBounds
                )
            }

            DetectionInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .weight(1f),
                username = detectionResult.username,
                date = detectionResult.date,
                time = detectionResult.time,
                color = YellowAppColor,
                fontSize = 15
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(imageAndData.bottom, margin = 22.dp)
                    bottom.linkTo(boxes.top, margin = 22.dp)
                }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(31.dp),
                painter = painterResource(id = getCropIcon(Crop.valueOf(detectionResult.crop))),
                contentDescription = "crop icon"
            )
            YellowBlackText(size = 24, text = detectionResult.crop)
        }

        if (detectionResult.isHealthy) {
            DetectionResult(
                name = stringResource(id = R.string.healthy),
                confidence = detectionResult.confidence.toString(),
                modifier = Modifier
                    .constrainAs(boxes) {
                        top.linkTo(text.bottom, margin = Dimens.content_margin)
                        bottom.linkTo(bottomGuideLine)
                    }
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(5.dp)),
                alignment = Alignment.CenterHorizontally
            )

        } else {
            LazyColumn(
                modifier = Modifier
                    .constrainAs(boxes) {
                        top.linkTo(text.bottom, margin = Dimens.content_margin)
                        bottom.linkTo(bottomGuideLine)
                    },
            ) {
                items(detectionResult.possibleDiseases!!.size) { index ->
                    val disease = detectionResult.possibleDiseases[index]
                    DetectionLowConfidence(
                        modifier = Modifier.padding(vertical = 8.dp),
                        confidence = disease.confidence.toString(),
                        name = disease.name,
                        infoLink = disease.infoLink
                    )
                }

            }

        }
    }
}

@Preview
@Composable
fun DetectionDetailsPreview() {
    DetectionDetails(
        rememberNavController(),
        0
    )
}