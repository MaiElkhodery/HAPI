package com.example.hapi.presentation.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.Logo
import com.example.hapi.presentation.home.farmer.navigateToFarmerHome
import com.example.hapi.presentation.home.landowner.navigateToLandownerHome
import com.example.hapi.presentation.identityselection.Crops
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.Tab

@Composable
fun ProgressScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    final: String = "false",
    isFarmer: Boolean = false
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenHeight = maxHeight

        val topPadding = screenHeight * 0.1f
        val contentMargin = if (screenHeight < 600.dp) 16.dp else Dimens.content_margin

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(topPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(contentMargin))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                if (final == "true") {
                    SetupMessage(
                        message = stringResource(id = R.string.congratulation)
                    ) {
                        mainViewModel.setSelectedTab(Tab.HOME)
                        if (isFarmer)
                            navController.navigateToFarmerHome()
                        else
                            navController.navigateToLandownerHome()
                    }
                } else {
                    SetupMessage(
                        message = stringResource(id = R.string.account_setup)
                    ) {
                        navController.navigateToIdentitySelection()
                    }
                }
            }

            Spacer(modifier = Modifier.height(contentMargin))

            Crops(
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun ProgressPreview() {
    ProgressScreen(rememberNavController())
}