package com.example.hapi.presentation.role_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.auth.signup.farmersignup.navToFarmerSignup
import com.example.hapi.presentation.auth.signup.landownersignup.signup.navToLandownerSignup
import com.example.hapi.presentation.common.Logo
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun RoleOptions(navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxHeight

        val largePadding = screenHeight * 0.06f
        val smallPadding = screenHeight * 0.03f
        val logoSize = when {
            screenHeight <= 600.dp -> 120.dp
            screenHeight in 600.dp..855.dp -> 185.dp
            else -> 200.dp
        }
        val roleImageSize = when {
            screenHeight <= 600.dp -> 70.dp
            screenHeight in 600.dp..855.dp -> 90.dp
            else -> 95.dp
        }

        val fontSize = when {
            screenWidth <= 360.dp -> 10
            screenWidth in 360.dp..400.dp -> 16
            else -> 18
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(largePadding))

            Logo(modifier = Modifier.size(logoSize))

            Spacer(modifier = Modifier.height(smallPadding))

            RoleOptions(
                fontSize = fontSize,
                imageSize = roleImageSize,
                onClickLandowner = {
                    navController.navToLandownerSignup()
                },
                onClickFarmer = {
                    navController.navToFarmerSignup()
                }
            )

            Spacer(modifier = Modifier.height(smallPadding))

            CropImage(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun IdentitySelectionScreenPreview() {
    RoleOptions(rememberNavController())
}