package com.example.hapi.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun Main(navController: NavController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val topGuideLine = createGuidelineFromTop(.11f)
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp, vertical = 36.dp)
                .constrainAs(signup) {
                    top.linkTo(welcomeImage.bottom)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MediumYellowText(
                text = stringResource(id = R.string.first_time),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            MainButton(text = stringResource(id = R.string.signup)) {
                //TODO: NAV TO SIGN UP
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp)
                .constrainAs(signin) {
                    top.linkTo(signup.bottom)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MediumYellowText(
                text = stringResource(id = R.string.have_account),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            MainButton(text = stringResource(id = R.string.signin)) {
                //TODO: NAV TO SIGN IN
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .constrainAs(guestRow) {
                    bottom.linkTo(cropImage.top)
                }
                .clickable {
                    //TODO: NAV TO GUEST HOME SCREEN
                },
            horizontalArrangement = Arrangement.Center
        ) {
            MediumYellowText(
                text = stringResource(id = R.string.or),
                modifier = Modifier.padding(end = 3.dp)
            )
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.detect_now),
                color = YellowAppColor,
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_black
                    )
                )
            )
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "go to guest home",
                tint = YellowAppColor
            )
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

@Composable
fun MediumYellowText(
    text: String,
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        color = YellowAppColor,
        fontSize = 15.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_medium
            )
        )
    )
}

@Preview
@Composable
fun MainPreview() {
    Main(navController = rememberNavController())
}