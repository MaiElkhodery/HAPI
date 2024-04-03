package com.example.hapi.presentation.auth.signup.landownersignup.recommendation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signup.common.Logo
import com.example.hapi.presentation.auth.signup.common.NavHeader
import com.example.hapi.presentation.auth.signup.common.Title
import com.example.hapi.presentation.auth.signup.landownersignup.detection.navToCropDetection
import com.example.hapi.presentation.auth.signup.landownersignup.finalcrop.navigateToFinalCrop
import com.example.hapi.presentation.auth.signup.landownersignup.info.LotusRow
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Crop
import com.example.hapi.util.Dimens

@Composable
fun CropRecommendationScreen(
    navController: NavController,
//    viewModel: LandownerViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (logo, header, title, content, lotusRow) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_sign)
        val bottomGuideLine = createGuidelineFromBottom(Dimens.bottom_guideline_sign)

        Logo(
            modifier = Modifier
                .fillMaxWidth()
                .size(70.dp)
                .constrainAs(logo) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(header.top)
                }
        )
        NavHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(logo.bottom, margin = Dimens.header_margin)
                bottom.linkTo(title.top, margin = Dimens.header_margin)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        ) {
            navController.navToCropDetection()
        }

        Title(title = stringResource(id = R.string.choose_recommedation),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(header.bottom)
                bottom.linkTo(content.top, margin = Dimens.title_bottom_margin)
            }
        )

        CropRecommendationContent(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(lotusRow.top, margin = Dimens.content_margin)
                },
            //TEMPORARY
            topCrops = listOf(
                Crop.POTATO,
                Crop.TOMATO,
                Crop.CORN
            )
        ) { crop ->
            navController.navigateToFinalCrop(crop.name)
        }
        LotusRow(
            highlightedLotusPos = 2,
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
private fun FinalCropScreenPreview() {
    CropRecommendationScreen(
        rememberNavController()
    )
}