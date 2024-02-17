package com.example.hapi.presentation.signup.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.signup.common.Logo
import com.example.hapi.presentation.signup.farmersignup.ui.navToFarmerSignup
import com.example.hapi.presentation.signup.landownersignup.info.navToLandownerSignup
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun IdentitySelectionScreen(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val topGuideLine = createGuidelineFromTop(.1f)
        val (logoBox, cropBox, contentBox) = createRefs()

        Logo(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(logoBox) {
                top.linkTo(topGuideLine)
                bottom.linkTo(contentBox.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .constrainAs(contentBox) {
                    top.linkTo(logoBox.bottom, margin = Dimens.content_margin)
                    bottom.linkTo(cropBox.top, margin = Dimens.content_margin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {


            IdentitySelection(
                onClickLandowner = {
                    navController.navToLandownerSignup()
                },
                onclickFarmer = {
                    navController.navToFarmerSignup()
                }
            )

        }

        Crops(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(cropBox) {
                bottom.linkTo(parent.bottom)
                top.linkTo(contentBox.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }

}

@Preview
@Composable
private fun IdentitySelectionScreenPreview() {
    IdentitySelectionScreen(rememberNavController())
}