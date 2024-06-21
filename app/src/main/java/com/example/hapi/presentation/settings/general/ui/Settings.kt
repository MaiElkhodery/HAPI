package com.example.hapi.presentation.settings.general.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.language_setup.navigateToLanguageSetUp
import com.example.hapi.presentation.settings.about.navigateToAboutUs
import com.example.hapi.presentation.settings.common.LandIdRow
import com.example.hapi.presentation.settings.common.WarningDialogWithPassword
import com.example.hapi.presentation.settings.data_and_storage.ui.navigateToDataAndStorage
import com.example.hapi.presentation.settings.data_and_storage.viewmodel.DataAndStorageViewModel
import com.example.hapi.presentation.settings.farmers.ui.navigateToFarmers
import com.example.hapi.presentation.settings.general.viewmodel.SettingsEvent
import com.example.hapi.presentation.settings.general.viewmodel.SettingsViewModel
import com.example.hapi.presentation.settings.language.navigateToLanguageSettings
import com.example.hapi.presentation.settings.support.navigateToHelpAndSupport
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.LANDOWNER

@Composable
fun Settings(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
    dataAndStorageViewModel: DataAndStorageViewModel = hiltViewModel()
) {

    val landId = viewModel.landId.collectAsState().value
    val role = viewModel.role.collectAsState().value
    val isLoggedOut = viewModel.isLoggedOut.collectAsState().value

    var openDialog by remember { mutableStateOf(false) }
    var openDialogWithPassword by remember { mutableStateOf(false) }

    var warningText by remember { mutableStateOf("") }
    var additionalWarningText by remember { mutableStateOf("") }
    var onClickConfirm by remember { mutableStateOf({}) }

    val logoutWarning = stringResource(id = R.string.logout)
    val deleteAccountWarning = stringResource(id = R.string.delete_your_account)
    val clearDetectionWarning = stringResource(id = R.string.clear_detection_history)

    LaunchedEffect(isLoggedOut) {
        if (isLoggedOut)
            navController.navigateToLanguageSetUp()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (welcomeHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_settings)
        val bottomGuideLine = createGuidelineFromBottom(Dimens.bottom_guideline_settings)
        val startGuideLine = createGuidelineFromStart(Dimens.start_guideline_settings)
        val endGuideLine = createGuidelineFromEnd(Dimens.end_guideline_settings)


        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(welcomeHeader) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(content.top)
                },
            imageId = R.drawable.logo,
            topText = stringResource(id = R.string.your),
            downText = stringResource(id = R.string.settings),
            imageSize = 80
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 33.dp)
                .constrainAs(content) {
                    top.linkTo(welcomeHeader.bottom, margin = 26.dp)
                    bottom.linkTo(bottomGuideLine)
                    start.linkTo(startGuideLine)
                    end.linkTo(endGuideLine)
                }
        ) {

            LandIdRow(landId = landId)

            if (role == LANDOWNER)
                LandownerSettingsOptions(
                    modifier = Modifier.padding(top = 22.dp),
                    onLanguageClick = { navController.navigateToLanguageSettings() },
                    onFarmersListClick = { navController.navigateToFarmers() },
                    onDataAndStorageClick = { navController.navigateToDataAndStorage() },
                    onHelpAndSupportClick = { navController.navigateToHelpAndSupport() },
                    onAboutUsClick = { navController.navigateToAboutUs() },
                    onDeleteAccountClick = {
                        openDialog = true
                        openDialogWithPassword = true
                        warningText = deleteAccountWarning
                        onClickConfirm = {
                            viewModel.onEvent(SettingsEvent.OnClickDeleteAccount)
                        }
                    },
                    onLogoutClick = {
                        openDialog = true
                        openDialogWithPassword = true
                        warningText = logoutWarning
                        onClickConfirm = {
                            viewModel.onEvent(SettingsEvent.OnClickLogout)
                        }
                    }
                )
            else
                FarmerSettingsOptions(
                    modifier = Modifier.padding(top = 22.dp),
                    onLanguageClick = { navController.navigateToLanguageSettings() },
                    onClearDetectionClick = {
                        openDialog = true
                        openDialogWithPassword = false
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
                        openDialogWithPassword = true
                        warningText = deleteAccountWarning
                        onClickConfirm = {
                            viewModel.onEvent(SettingsEvent.OnClickDeleteAccount)
                        }
                    },
                    onLogoutClick = {
                        openDialog = true
                        openDialogWithPassword = true
                        warningText = logoutWarning
                        onClickConfirm = {
                            viewModel.onEvent(SettingsEvent.OnClickLogout)
                        }
                    }
                )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (openDialog) {
            WarningDialogWithPassword(
                withPassword = openDialogWithPassword,
                warningText = warningText,
                additionalWarningText = additionalWarningText,
                password = viewModel.password,
                onClickConfirm = onClickConfirm,
                onChangePassword = { password ->
                    viewModel.onEvent(SettingsEvent.ChangePassword(password))
                },
                onClickCancel = { openDialog = false }
            )
        }
    }
}

@Preview
@Composable
private fun LandownerSettingsPreview() {
    Settings(navController = rememberNavController())
}