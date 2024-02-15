package com.example.hapi.presentation.signup.landownersignup.choose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.hapi.data.model.crops
import com.example.hapi.presentation.signup.common.Logo
import com.example.hapi.presentation.signup.common.SignupAndGuestHeader
import com.example.hapi.presentation.signup.common.Title
import com.example.hapi.presentation.signup.landownersignup.finalcrop.navToFinalCropScreen
import com.example.hapi.presentation.signup.landownersignup.info.LotusRow
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun CropChooseScreen(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (logo,header, content, title, lotusRow) = createRefs()
        val topGuideLine = createGuidelineFromTop(.02f)
        val bottomGuideLine = createGuidelineFromBottom(.11f)

        Logo(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(logo) {
                top.linkTo(topGuideLine)
                bottom.linkTo(header.top)
            }
        )
        SignupAndGuestHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(logo.bottom)
                bottom.linkTo(content.top)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        ) {

        }

        Title(title = stringResource(id = R.string.avilable_crop),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(header.bottom)
                bottom.linkTo(content.top)
            })

        CropChooseContent(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(lotusRow.top)
                },
            crops = crops
        ) { crop ->
            navController.navToFinalCropScreen(crop)
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
private fun CropChoosePreview() {
    CropChooseScreen(rememberNavController())
}