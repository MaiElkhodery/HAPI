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
import androidx.compose.runtime.Composable
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
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.LotusRow
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.SignLabeledInputFields
import com.example.hapi.presentation.common.WarningBox
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor

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
    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 24.dp)
    ) {

        val screenHeight = maxHeight

        val topPadding = screenHeight * 0.02f
        val contentPadding = screenHeight * 0.03f
        val bottomPadding = screenHeight * 0.04f
        val logoSize = if (screenHeight < 600.dp) 60.dp else 90.dp
        val backIconSize = if (screenHeight < 600.dp) 60 else 80

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(topPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )

            Spacer(modifier = Modifier.height(topPadding))

            NavHeader(
                modifier = Modifier,
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize
            ) {
                navController.navigateToIdentitySelection()
            }

            Spacer(modifier = Modifier.height(contentPadding))


            SignLabeledInputFields(
                title = stringResource(id = R.string.phone_number),
                content = viewModel.phoneNumber
            ) { phoneNumber ->
                viewModel.onEvent(AuthEvent.ChangePhoneNumber(phoneNumber))
            }

            WarningBox(warningText = phoneNumberError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.user_name),
                content = viewModel.username
            ) { username ->
                viewModel.onEvent(AuthEvent.ChangeUserName(username))
            }

            WarningBox(warningText = usernameError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.password),
                content = viewModel.password
            ) { password ->
                viewModel.onEvent(AuthEvent.ChangePassword(password))
            }

            WarningBox(warningText = passwordError)

            ConfirmButton(
                modifier = Modifier,
                text = stringResource(id = R.string.confirm)
            ) {
                viewModel.signupLandowner()
            }

            Spacer(modifier = Modifier.height(bottomPadding))

            LotusRow(
                highlightedLotusPos = 0,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(bottomPadding))
        }
    }
    if (authenticated) {
        navController.navigateToCropSelectionStrategy()
    }
}

@Preview
@Composable
fun LandownerSignupPreview() {
    LandownerSignup(navController = rememberNavController())
}