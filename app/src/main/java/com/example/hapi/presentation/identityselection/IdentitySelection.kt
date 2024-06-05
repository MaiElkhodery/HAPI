package com.example.hapi.presentation.identityselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun IdentitySelection(navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight

        val topPadding = screenHeight * 0.06f
        val logoSize = if (screenHeight < 600.dp) 140.dp else 180.dp
        val cropImageHeight = if (screenHeight < 600.dp) 200.dp else 300.dp

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(topPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )

            IdentitySelection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                onClickLandowner = {
                    navController.navToLandownerSignup()
                },
                onClickFarmer = {
                    navController.navToFarmerSignup()
                }
            )

            CropImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cropImageHeight)
            )
        }
    }
}

@Preview
@Composable
private fun IdentitySelectionScreenPreview() {
    IdentitySelection(rememberNavController())
}