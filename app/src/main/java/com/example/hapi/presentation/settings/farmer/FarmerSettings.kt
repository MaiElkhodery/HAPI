package com.example.hapi.presentation.settings.farmer

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
import com.example.hapi.presentation.settings.WarningDialogWithPassword
import com.example.hapi.presentation.settings.about.navigateToAboutUs
import com.example.hapi.presentation.settings.common.LandIdRow
import com.example.hapi.presentation.settings.data.DataAndStorageViewModel
import com.example.hapi.presentation.settings.language.navigateToLanguageSettings
import com.example.hapi.presentation.settings.support.navigateToHelpAndSupport
import com.example.hapi.presentation.welcome.navigateToWelcomeScreen
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun FarmerSettings(
    navController: NavController,
    viewModel: FarmerSettingsViewModel = hiltViewModel(),
    dataAndStorageViewModel: DataAndStorageViewModel = hiltViewModel()
) {
    val logout = viewModel.logout.collectAsState().value
    val deleteAccount = viewModel.deleteAccount.collectAsState().value
    val landId = viewModel.landId.collectAsState().value
    val isLoggedOut = viewModel.isLoggedOut.collectAsState().value

    var openDialog by remember { mutableStateOf(false) }
    var withPassword by remember {
        mutableStateOf(false)
    }
    var warningText by remember { mutableStateOf("") }
    var additionalWarningText by remember { mutableStateOf("") }
    var onClickConfirm by remember { mutableStateOf({}) }

    val logoutWarning = stringResource(id = R.string.logout)
    val deleteAccountWarning = stringResource(id = R.string.delete_your_account)
    val clearDetectionWarning = stringResource(id = R.string.clear_detection_history)

    LaunchedEffect(key1 = Unit, logout, deleteAccount) {
        viewModel.getLandId()
        if (logout)
            viewModel.logout()
        if (deleteAccount) {
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

            FarmerSettingsContent(
                modifier = Modifier.padding(top = 11.dp),
                onLanguageClick = { navController.navigateToLanguageSettings() },
                onClearDetectionClick = {
                    openDialog = true
                    withPassword = false
                    warningText = clearDetectionWarning
                    onClickConfirm = {
                        dataAndStorageViewModel.deleteDetectionHistory()
                        openDialog = false
                    }
                },
                onHelpAndSupportClick = { navController.navigateToHelpAndSupport() },
                onAboutUsClick = { navController.navigateToAboutUs() },
                onDeleteAccountClick = {
                    openDialog = true
                    withPassword = true
                    warningText = deleteAccountWarning
                    onClickConfirm = {
                        viewModel.onEvent(FarmerSettingsEvent.OnClickDeleteAccount)
                    }
                },
                onLogoutClick = {
                    openDialog = true
                    withPassword = true
                    warningText = logoutWarning
                    onClickConfirm = {
                        viewModel.onEvent(FarmerSettingsEvent.OnClickLogout)
                    }
                }
            )
        }

        if (isLoggedOut) {
            navController.navigateToWelcomeScreen()
        }
        if (openDialog) {
            WarningDialogWithPassword(
                isWithPassword = withPassword,
                warningText = warningText,
                additionalWarningText = additionalWarningText,
                password = viewModel.password,
                onClickConfirm = onClickConfirm,
                onChangePassword = { password ->
                    viewModel.onEvent(FarmerSettingsEvent.ChangePassword(password))
                },
                onClickCancel = { openDialog = false }
            )
        }
    }
}

@Preview
@Composable
private fun FarmerSettingsPreview() {
    FarmerSettings(navController = rememberNavController())
}