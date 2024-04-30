package com.example.hapi.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.home.common.CustomNavigationBottom
import com.example.hapi.presentation.home.cropselection.CropSelection
import com.example.hapi.presentation.home.farmer.FarmerHome
import com.example.hapi.presentation.home.landowner.LandownerHome
import com.example.hapi.presentation.settings.landowner.LandownerSettings
import com.example.hapi.util.LANDOWNER

@Composable
fun HapiMainScreen(
    navController: NavController,
    role: String
) {

    var isHomeSelected by remember { mutableStateOf(true) }
    var isCameraSelected by remember { mutableStateOf(false) }
    var isSettingsSelected by remember { mutableStateOf(false) }


    Scaffold(
        bottomBar = {
            CustomNavigationBottom(
                onHomeClick = {
                    isHomeSelected = true
                    isCameraSelected = false
                    isSettingsSelected = false
                },
                onCameraClick = {
                    isHomeSelected = false
                    isCameraSelected = true
                    isSettingsSelected = false
                },
                onSettingsClick = {
                    isHomeSelected = false
                    isCameraSelected = false
                    isSettingsSelected = true
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            when {
                isHomeSelected -> {
                    if (role == LANDOWNER)
                        LandownerHome(navController = navController)
                    else
                        FarmerHome(navController = navController)
                }

                isCameraSelected -> {
                    CropSelection(navController = navController)
                }

                isSettingsSelected -> {
                    if (role == LANDOWNER)
                        LandownerSettings(navController = navController)
                    //TODO: DISPLAY FARMER SETTINGS
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