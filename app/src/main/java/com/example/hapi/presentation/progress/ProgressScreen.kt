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
import androidx.compose.foundation.layout.size
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
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.role_selection.CropImage
import com.example.hapi.presentation.role_selection.navigateToRoleSelection
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.main.navigateToMainScreen
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.FARMER
import com.example.hapi.util.LANDOWNER
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
        val screenWidth = maxWidth

        val topPadding = screenHeight * 0.1f
        val iconSize = when {
            screenWidth <= 360.dp -> 24.dp
            else -> 28.dp
        }
        val fontSize = when {
            screenWidth <= 360.dp -> 18
            screenWidth in 360.dp..400.dp -> 20
            else -> 22
        }

        val logoSize = when {
            screenHeight <= 600.dp -> 130.dp
            screenHeight in 600.dp..855.dp -> 185.dp
            else -> 200.dp
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(topPadding))

            Logo(modifier = Modifier.size(logoSize))

            Spacer(modifier = Modifier.height(topPadding))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (final == "true") {
                    SetupMessage(
                        fontSize = fontSize,
                        iconSize = iconSize,
                        message = stringResource(id = R.string.congratulation)
                    ) {
                        mainViewModel.setSelectedTab(Tab.HOME)
                        val role = if (isFarmer) FARMER else LANDOWNER
                        if (isFarmer)
                            navController.navigateToMainScreen(role = role)
                        else
                            navController.navigateToMainScreen(role = role)
                    }
                } else {
                    SetupMessage(
                        fontSize = fontSize,
                        iconSize = iconSize,
                        message = stringResource(id = R.string.account_setup)
                    ) {
                        navController.navigateToRoleSelection()
                    }
                }
            }

            Spacer(modifier = Modifier.height(topPadding))

            CropImage(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
private fun ProgressPreview() {
    ProgressScreen(rememberNavController())
}