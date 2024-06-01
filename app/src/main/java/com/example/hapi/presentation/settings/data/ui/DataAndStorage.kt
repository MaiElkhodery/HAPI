package com.example.hapi.presentation.settings.data.ui

import androidx.compose.foundation.background
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
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.navigateToCropSelectionStrategy
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.WarningDialogWithPassword
import com.example.hapi.presentation.settings.data.viewmodel.DataAndStorageViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.Tab

@Composable
fun DataAndStorage(
    mainViewModel: MainViewModel = hiltViewModel(),
    dataAndStorageViewModel: DataAndStorageViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController
) {

    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value
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

    LaunchedEffect(key1 = passwordConfirmed) {
        if (passwordConfirmed) {
            dataAndStorageViewModel.checkIfPasswordIsCorrect()
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(bottom = 26.dp)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_settings_options)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = if (isEnglish) R.string.data_storage else R.string.settings),
            downText = stringResource(id = if (isEnglish) R.string.settings else R.string.data_and_storage),
            imageId = if (isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar,
            imageSize = 80
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }

        DataAndStorageOptions(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                },
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

        if (openDialog) {
            WarningDialogWithPassword(
                isWithPassword = withPassword,
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

        if (isPasswordCorrect)
            navController.navigateToCropSelectionStrategy()
    }

}

@Preview
@Composable
fun DataAndStoragePreview() {
    DataAndStorage(navController = rememberNavController())
}