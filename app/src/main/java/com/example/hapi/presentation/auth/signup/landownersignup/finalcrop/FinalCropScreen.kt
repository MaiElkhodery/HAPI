package com.example.hapi.presentation.auth.signup.landownersignup.finalcrop

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
import com.example.hapi.data.model.Crop
import com.example.hapi.data.model.crops
import com.example.hapi.presentation.auth.signup.common.Logo
import com.example.hapi.presentation.auth.signup.common.SignupAndGuestHeader
import com.example.hapi.presentation.auth.signup.landownersignup.info.LotusRow
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun FinalCropScreen(
    navController: NavController,
//    viewModel: LandownerViewModel = hiltViewModel(),
    crop: Crop
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (logo, header, content, lotusRow) = createRefs()
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
        SignupAndGuestHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(logo.bottom, margin = Dimens.header_margin)
                bottom.linkTo(content.top, margin = Dimens.header_margin)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        ) {
            navController.popBackStack()
        }

        FinalCropContent(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(header.bottom)
                bottom.linkTo(lotusRow.top, margin = Dimens.content_margin)
            },
            crop = crop
        ) {
            navController.navToProgress(final = "true")
        }

        LotusRow(
            highlightedLotusPos = 3,
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
    FinalCropScreen(rememberNavController(), crops[1])
}