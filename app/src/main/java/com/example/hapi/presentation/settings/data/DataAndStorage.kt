package com.example.hapi.presentation.settings.data

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
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.navigateToCropSelectionStrategy
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.WarningDialogWithPassword
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Tab

@Composable
fun DataAndStorage(
    mainViewModel: MainViewModel = hiltViewModel(),
    dataAndStorageViewModel: DataAndStorageViewModel = hiltViewModel(),
    navController: NavController
) {

    val isPasswordCorrect = dataAndStorageViewModel.isPasswordCorrect.collectAsState().value
    var warningText by remember { mutableStateOf("") }
    var additionalWarningText by remember { mutableStateOf("") }
    var onClickConfirm by remember { mutableStateOf({}) }
    var openDialog by remember { mutableStateOf(false) }
    var withPassword by remember { mutableStateOf(false) }
    var passwordConfirmed by remember { mutableStateOf(false) }

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
        val topGuideLine = createGuidelineFromTop(.05f)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = R.string.data_storage),
            downText = stringResource(id = R.string.settings),
            imageId = R.drawable.settings_back_btn
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }

        DataAndStorageContent(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                },
            onClickClearDetectionHistory = {
                openDialog = true
                withPassword = false
                warningText = "RESET DETECTION\nHISTORY?"
                onClickConfirm = {
                    dataAndStorageViewModel.deleteDetectionHistory()
                    openDialog = false
                }
            },
            onClickClearLandHistory = {
                openDialog = true
                withPassword = false
                warningText = "RESET LAND\nHISTORY?"
                onClickConfirm = {
                    dataAndStorageViewModel.deleteLandHistory()
                    openDialog = false
                }
            },
            onClickChangeCrop = {
                openDialog = true
                withPassword = true
                warningText = "CHANGE CROP?"
                additionalWarningText = "THIS ALSO RESETS ALL LAND HISTORY"
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