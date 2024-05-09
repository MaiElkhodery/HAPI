package com.example.hapi.presentation.settings.landowner

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.settings.WarningDialog
import com.example.hapi.presentation.settings.WarningDialogWithPassword
import com.example.hapi.presentation.settings.about.navigateToAboutUs
import com.example.hapi.presentation.settings.common.LandIdRow
import com.example.hapi.presentation.settings.data.navigateToDataAndStorage
import com.example.hapi.presentation.settings.farmers.navigateToFarmers
import com.example.hapi.presentation.settings.support.navigateToHelpAndSupport
import com.example.hapi.presentation.welcome.navigateToWelcomeScreen
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun LandownerSettings(
    navController: NavController,
    viewModel: LandownerSettingsViewModel = hiltViewModel()
) {
    val logout = viewModel.logout.collectAsState().value
    val deleteAccount = viewModel.deleteAccount.collectAsState().value
    val landId = viewModel.landId.collectAsState().value
    val isLoggedOut = viewModel.isLoggedOut.collectAsState().value

    var openDialogWithPassword by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var warningText by remember { mutableStateOf("") }
    var additionalWarningText by remember { mutableStateOf("") }
    var onClickConfirm by remember { mutableStateOf({}) }

    LaunchedEffect(key1 = Unit, logout, deleteAccount) {
        viewModel.getLandId()
        if (logout)
            viewModel.logout()
        if (deleteAccount) {
            Log.d("LandownerSettings", "LandownerSettings: delete account")
            viewModel.deleteAccount()
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(bottom = 26.dp)
    ) {

        val (welcomeHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(.08f)


        NavHeader(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(welcomeHeader) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(content.top)
                },
            imageId = R.drawable.logo,
            topText = stringResource(id = R.string.your),
            downText = stringResource(id = R.string.settings),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(content) {
                    top.linkTo(welcomeHeader.bottom, margin = 26.dp)
                }
        ) {

            LandIdRow(landId = landId)

            LandownerSettingsContent(
                modifier = Modifier.padding(top = 11.dp),
                onLanguageClick = { /*TODO*/ },
                onFarmersListClick = { navController.navigateToFarmers() },
                onDataAndStorageClick = { navController.navigateToDataAndStorage() },
                onHelpAndSupportClick = { navController.navigateToHelpAndSupport() },
                onAboutUsClick = { navController.navigateToAboutUs() },
                onDeleteAccountClick = {
                    openDialogWithPassword = true
                    warningText = "DELETE YOUR\nACCOUNT?"
                    onClickConfirm = {
                        Log.d("LandownerSettings", "LandownerSettings: delete account")
                        viewModel.onEvent(LandownerSettingsEvent.OnClickDeleteAccount)
                    }
                },
                onLogoutClick = {
                    openDialog = true
                    warningText = "LOG OUT?"
                    onClickConfirm = {
                        viewModel.onEvent(LandownerSettingsEvent.OnClickLogout)
                    }
                }
            )
        }

        if (isLoggedOut) {
            navController.navigateToWelcomeScreen()
        }
        if (openDialogWithPassword) {
            WarningDialogWithPassword(
                warningText = warningText,
                additionalWarningText = additionalWarningText,
                password = viewModel.password,
                onClickConfirm = onClickConfirm,
                onChangePassword = { password ->
                    viewModel.onEvent(LandownerSettingsEvent.ChangePassword(password))
                },
                onClickCancel = { openDialogWithPassword = false }
            )
        }

        if (openDialog) {
            WarningDialog(
                warningText = warningText,
                additionalWarningText = additionalWarningText,
                onClickConfirm = onClickConfirm,
                onClickCancel = { openDialog = false }
            )
        }
    }
}

@Preview
@Composable
private fun LandownerSettingsPreview() {
    LandownerSettings(navController = rememberNavController())
}