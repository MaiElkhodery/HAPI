package com.example.hapi.presentation.home.landowner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.data.model.Detection
import com.example.hapi.presentation.home.common.CustomNavigationBottom
import com.example.hapi.presentation.home.common.CustomNavigationBottomBackground
import com.example.hapi.presentation.home.common.HistoryCard
import com.example.hapi.presentation.home.common.HomeHeader
import com.example.hapi.presentation.home.common.LandData
import com.example.hapi.presentation.home.detectionhistory.navigateToDetectionHistory
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.LandAction

@Composable
fun LandownerHome(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (header, content, historyCards, navBottom,bottomBackground) = createRefs()
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

        LandData(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .constrainAs(content) {
                    top.linkTo(header.bottom, margin = 21.dp)
                    bottom.linkTo(historyCards.top, margin = 22.dp)
                },
            lastLandAction = com.example.hapi.data.model.LandAction(
                LandAction.FERTILIZATION.name,
                "2021-09-01",
                "12:00"
            ),
            lastDetection = Detection(
                username = "John Doe",
                date = "12/12/2021",
                time = "12:00",
                imagePath = "",
                possibleDiseases = emptyList(),
                crop = "",
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .constrainAs(historyCards) {
                    top.linkTo(content.bottom)
                    bottom.linkTo(navBottom.top, margin = 26.dp)
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
                .padding(top = 16.dp)
                .constrainAs(navBottom) {
                    bottom.linkTo(parent.bottom)
                },
            onHomeClick = { },
            onCameraClick = { },
            onSettingsClick = { }
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