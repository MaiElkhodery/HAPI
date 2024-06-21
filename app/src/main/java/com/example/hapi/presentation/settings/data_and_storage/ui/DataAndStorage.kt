package com.example.hapi.presentation.settings.data_and_storage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.navigateToCropSelectionStrategy
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.common.WarningDialogWithPassword
import com.example.hapi.presentation.settings.data_and_storage.viewmodel.DataAndStorageViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.Tab

@Composable
fun DataAndStorage(
    mainViewModel: MainViewModel = hiltViewModel(),
    dataAndStorageViewModel: DataAndStorageViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    val isPasswordCorrect = dataAndStorageViewModel.isPasswordCorrect.collectAsState().value

    var warningText by remember { mutableStateOf("") }
    var additionalWarningText by remember { mutableStateOf("") }
    var onClickConfirm by remember { mutableStateOf({}) }
    var openDialog by remember { mutableStateOf(false) }
    var withPassword by remember { mutableStateOf(false) }
    var passwordConfirmed by remember { mutableStateOf(false) }

    val clearDetectionHistoryWarning = stringResource(id = R.string.clear_detection_history)
    val clearLandHistoryWarning = stringResource(id = R.string.clear_land_history)
    val changeCropWarning = stringResource(id = R.string.change_crop)
    val additionalWarningForChangeCrop = stringResource(id = R.string.change_crop_warning)

    LaunchedEffect(passwordConfirmed, isPasswordCorrect) {
        if (passwordConfirmed)
            dataAndStorageViewModel.checkIfPasswordIsCorrect()

        if (isPasswordCorrect)
            navController.navigateToCropSelectionStrategy()
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val horizontalPadding = if (screenWidth < 360.dp) 12.dp else 16.dp
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val padding = screenHeight * 0.03f
        val headerFontSize = when {
            screenWidth <= 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }

        Column(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHeader(
                modifier = Modifier.padding(vertical = 12.dp),
                topText = stringResource(id = if (isEnglish) R.string.data_storage else R.string.settings),
                downText = stringResource(id = if (isEnglish) R.string.settings else R.string.data_and_storage),
                imageId = if (isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar,
                imageSize = backIconSize,
                fontSize = headerFontSize
            ) {
                mainViewModel.setSelectedTab(Tab.SETTINGS)
                navController.popBackStack()
            }

            DataAndStorageOptions(
                modifier = Modifier.padding(horizontal = 12.dp),
                width = screenWidth,
                height = screenHeight,
                onClickClearDetectionHistory = {
                    openDialog = true
                    withPassword = false
                    warningText = clearDetectionHistoryWarning
                    onClickConfirm = {
                        dataAndStorageViewModel.deleteDetectionHistory()
                        openDialog = false
                    }
                },
                onClickClearLandHistory = {
                    openDialog = true
                    withPassword = false
                    warningText = clearLandHistoryWarning
                    onClickConfirm = {
                        dataAndStorageViewModel.deleteLandHistory()
                        openDialog = false
                    }
                },
                onClickChangeCrop = {
                    openDialog = true
                    withPassword = true
                    warningText = changeCropWarning
                    additionalWarningText = additionalWarningForChangeCrop
                    onClickConfirm = {
                        passwordConfirmed = true
                    }
                }
            )

            Spacer(modifier = Modifier.height(padding))

            if (openDialog) {
                WarningDialogWithPassword(
                    withPassword = withPassword,
                    warningText = warningText,
                    additionalWarningText = additionalWarningText,
                    password = dataAndStorageViewModel.password,
                    onClickConfirm = onClickConfirm,
                    onChangePassword = { password ->
                        dataAndStorageViewModel.onChangePassword(password)
                    },
                    onClickCancel = { openDialog = false }
                )
            }

        }
    }

}

@Preview
@Composable
fun DataAndStoragePreview() {
    DataAndStorage(navController = rememberNavController())
}