package com.example.hapi.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.detection.cropselection.CropSelection
import com.example.hapi.presentation.home.common.CustomNavigationBottom
import com.example.hapi.presentation.home.farmer.FarmerHome
import com.example.hapi.presentation.home.landowner.ui.LandownerHome
import com.example.hapi.presentation.settings.farmer.ui.FarmerSettings
import com.example.hapi.presentation.settings.landowner.ui.LandownerSettings
import com.example.hapi.util.LANDOWNER
import com.example.hapi.util.Tab

@Composable
fun HapiMainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    role: String
) {

    val selectedTab = viewModel.selectedTab.collectAsState().value

    Scaffold(
        bottomBar = {
            CustomNavigationBottom(
                onHomeClick = {
                    viewModel.setSelectedTab(Tab.HOME)
                },
                onCameraClick = {
                    viewModel.setSelectedTab(Tab.CAMERA)
                },
                onSettingsClick = {
                    viewModel.setSelectedTab(Tab.SETTINGS)
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            when (selectedTab) {
                Tab.HOME -> {
                    if (role == LANDOWNER)
                        LandownerHome(navController = navController)
                    else
                        FarmerHome(navController = navController)
                }

                Tab.CAMERA -> {
                    CropSelection(navController = navController)
                }

                Tab.SETTINGS -> {
                    if (role == LANDOWNER)
                        LandownerSettings(navController = navController)
                    else
                        FarmerSettings(navController = navController)
                }
            }
        }
    }

}

@Preview
@Composable
private fun HapiMainScreenPreview() {
    HapiMainScreen(navController = rememberNavController(), role = "landowner")
}