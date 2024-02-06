package com.example.hapi.presentation.signup.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun ProgressScreen(navController: NavController) {

    var continueClicked by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val topGuideLine = createGuidelineFromTop(.1f)
        val (logoBox, cropBox, contentBox) = createRefs()
        createVerticalChain(logoBox,contentBox,cropBox, chainStyle = ChainStyle.Spread)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(logoBox) {
                    top.linkTo(topGuideLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(185.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .constrainAs(contentBox) {
                    top.linkTo(logoBox.bottom)
                    bottom.linkTo(cropBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            if (continueClicked) {
                IdentitySelection(
                    onClickLandowner = {
                        //TODO:NAV TO LANDOWNER SIGN UP
                    },
                    onclickFarmer = {
                        //TODO:NAV TO FARMER SIGN UP
                    }
                )
            } else {
                SetupMessage {
                    continueClicked = true
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(cropBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.crop_profile),
                contentDescription = "crop"
            )
        }
    }
}

@Preview
@Composable
fun ProgressPreview() {
    ProgressScreen(rememberNavController())
}