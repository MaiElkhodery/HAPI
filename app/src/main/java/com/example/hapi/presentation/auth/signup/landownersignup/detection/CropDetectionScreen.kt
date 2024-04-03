package com.example.hapi.presentation.auth.signup.landownersignup.detection

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
import com.example.hapi.presentation.auth.common.Logo
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.auth.common.Title
import com.example.hapi.presentation.auth.signup.landownersignup.choose.navToCropChooseScreen
import com.example.hapi.presentation.auth.signup.landownersignup.info.LotusRow
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun CropDetectionScreen(
    navController: NavController,
//    viewModel: LandownerViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (logo, header, content, title, lotusRow) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_sign)
        val bottomGuideLine = createGuidelineFromBottom(Dimens.bottom_guideline_sign)

        Logo(
            modifier = Modifier
                .fillMaxWidth()
                .size(70.dp)
                .constrainAs(logo) {
                    top.linkTo(topGuideLine)
                }
        )
        NavHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(logo.bottom, margin = Dimens.header_margin)
                bottom.linkTo(title.top)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        ) {

        }

        Title(title = stringResource(id = R.string.do_you),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(header.bottom, margin = Dimens.header_margin)
            }
        )

        CropDetectionContent(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(title.bottom, margin = Dimens.header_margin)
            },
            onClickRecommendation = {
                //TODO: RETURN OR FLOW THE RECOMMENDED CROPS
//                viewModel.cropRecommendation()

            },
            onClickHaveCrop = {
                navController.navToCropChooseScreen()
            }
        )

        LotusRow(
            highlightedLotusPos = 1,
            modifier = Modifier
                .padding(top = 18.dp)
                .constrainAs(lotusRow) {
                    top.linkTo(content.bottom, margin = Dimens.header_margin)
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