package com.example.hapi.presentation.signup.landownersignup.ui.cropdetection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.signup.common.SignupAndGuestHeader
import com.example.hapi.presentation.signup.landownersignup.ui.info.LotusRow
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun CropDetectionScreen(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (header, content, title, lotusRow) = createRefs()
        val topGuideLine = createGuidelineFromTop(.08f)
        val bottomGuideLine = createGuidelineFromBottom(.11f)

        SignupAndGuestHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(topGuideLine)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        )

        Title(title = stringResource(id = R.string.do_you),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(header.bottom)
                bottom.linkTo(content.top)
            })

        CropDetectionContent(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(title.bottom)
                bottom.linkTo(lotusRow.top)
            },
            onClickRecommendation = {
                //TODO: NAV TO CROP RECOMMENDATION SCREEN
            },
            onClickHaveCrop = {
                navController.navToCropChooseScreen()
            }
        )

        LotusRow(
            highlightedLotusPos = 1,
            modifier = Modifier
                .constrainAs(lotusRow) {
                    top.linkTo(content.bottom)
                    bottom.linkTo(bottomGuideLine)
                }
        )
    }
}

@Preview
@Composable
private fun CropRecommendationPreview() {
    CropDetectionScreen(rememberNavController())
}