package com.example.hapi.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signin.navToSignin
import com.example.hapi.presentation.home.common.ORTextRow
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun Main(navController: NavController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val topGuideLine = createGuidelineFromTop(Dimens.bottom_guideline_sign)
        val (welcomeImage, guestRow, signup, signin, cropImage) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(welcomeImage) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(signup.top)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcom_to_hapi),
                contentDescription = null
            )
        }

        MainScreenContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp)
                .constrainAs(signup) {
                    top.linkTo(welcomeImage.bottom, margin = Dimens.content_margin)
                    bottom.linkTo(signin.top)
                },
            text = stringResource(id = R.string.first_time),
            buttonText = stringResource(id = R.string.signup)
        ) {
            navController.navToProgress("false")
        }
        MainScreenContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp)
                .constrainAs(signin) {
                    top.linkTo(signup.bottom, margin = Dimens.content_margin)
                    bottom.linkTo(guestRow.top, margin = Dimens.content_margin)
                },
            text = stringResource(id = R.string.have_account),
            buttonText = stringResource(id = R.string.signin)
        ) {
            navController.navToSignin()
        }

        ORTextRow(
            modifier = Modifier
                .constrainAs(guestRow) {
                    bottom.linkTo(cropImage.top, margin = 5.dp)
                },
            text = stringResource(id = R.string.detect_now)
        ) {
            //TODO: NAV TO GUEST HOME SCREEN
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(cropImage) {
                    bottom.linkTo(parent.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.main_crop),
                contentDescription = null
            )
        }

    }

}


@Preview
@Composable
fun MainPreview() {
    Main(navController = rememberNavController())
}