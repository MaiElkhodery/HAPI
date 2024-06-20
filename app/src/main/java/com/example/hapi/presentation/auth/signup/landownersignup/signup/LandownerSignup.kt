package com.example.hapi.presentation.auth.signup.landownersignup.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.hapi.presentation.auth.viewmodel.AuthEvent
import com.example.hapi.presentation.auth.viewmodel.AuthViewModel
import com.example.hapi.presentation.common.AuthWarningBox
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.LabeledInputFields
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.LotusRow
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.ScreenSize
import com.example.hapi.util.getScreenWidth

@Composable
fun LandownerSignup(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val phoneNumberError = viewModel.phoneNumberError.collectAsState().value
    val usernameError = viewModel.usernameError.collectAsState().value
    val passwordError = viewModel.passwordError.collectAsState().value
    val authenticated = viewModel.authenticated.collectAsState().value
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    val scrollState = rememberScrollState()

    LaunchedEffect(authenticated) {
        if (authenticated) {
            navController.navigateToCropSelectionStrategy()
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxWidth

        val largePadding = screenHeight * 0.044f
        val smallPadding = screenHeight * 0.034f
        val logoSize = if (screenHeight < 650.dp) 55.dp else 75.dp
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val horizontalPadding = if (screenWidth < 400.dp) 24.dp else 28.dp
        val fontSize = when (getScreenWidth(screenWidth)) {
            ScreenSize.SMALL -> 11
            ScreenSize.NORMAL -> 15
            ScreenSize.LARGE -> 17
            ScreenSize.XLARGE -> 19
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(smallPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )

            Spacer(modifier = Modifier.height(smallPadding))

            NavHeader(
                modifier = Modifier,
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize,
                fontSize = fontSize
            ) {
                navController.navigateToIdentitySelection()
            }

            Spacer(modifier = Modifier.height(smallPadding))


            LabeledInputFields(
                width = screenWidth,
                height=screenHeight,
                title = stringResource(id = R.string.phone_number),
                content = viewModel.phoneNumber
            ) { phoneNumber ->
                viewModel.onEvent(AuthEvent.ChangePhoneNumber(phoneNumber))
            }

            AuthWarningBox(
                width = screenWidth,
                warningText = phoneNumberError
            )

            LabeledInputFields(
                width = screenWidth,
                height=screenHeight,
                title = stringResource(id = R.string.user_name),
                content = viewModel.username
            ) { username ->
                viewModel.onEvent(AuthEvent.ChangeUserName(username))
            }

            AuthWarningBox(
                width = screenWidth,
                warningText = usernameError
            )

            LabeledInputFields(
                height=screenHeight,
                width = screenWidth,
                title = stringResource(id = R.string.password),
                content = viewModel.password
            ) { password ->
                viewModel.onEvent(AuthEvent.ChangePassword(password))
            }

            AuthWarningBox(
                width = screenWidth,
                warningText = passwordError
            )

            Spacer(modifier = Modifier.height(smallPadding))


            ConfirmButton(
                width = screenWidth,
                modifier = Modifier,
                text = stringResource(id = R.string.confirm),
                isEnglish = isEnglish
            ) {
                viewModel.signupLandowner()
            }

            Spacer(modifier = Modifier.height(largePadding))

            LotusRow(highlightedLotusPos = 0)

            Spacer(modifier = Modifier.height(largePadding))
        }
    }

}

@Preview
@Composable
fun LandownerSignupPreview() {
    LandownerSignup(navController = rememberNavController())
}