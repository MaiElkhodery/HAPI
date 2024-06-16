package com.example.hapi.presentation.auth.signup.landownersignup.signup

import androidx.compose.foundation.background
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
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.LotusRow
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.SignLabeledInputFields
import com.example.hapi.presentation.common.SignWarningBox
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

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
    val isEnglish = languageViewModel.appLanguage.collectAsState().value== ENGLISH

    val scrollState = rememberScrollState()

    LaunchedEffect(authenticated){
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

        val smallPadding = screenHeight * 0.04f
        val largePadding = screenHeight * 0.03f
        val logoSize = if (screenHeight < 650.dp) 55.dp else 75.dp
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val horizontalPadding = if(screenWidth < 400.dp) 24.dp else 28.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
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
                imageSize = backIconSize
            ) {
                navController.navigateToIdentitySelection()
            }

            Spacer(modifier = Modifier.height(largePadding))


            SignLabeledInputFields(
                title = stringResource(id = R.string.phone_number),
                content = viewModel.phoneNumber
            ) { phoneNumber ->
                viewModel.onEvent(AuthEvent.ChangePhoneNumber(phoneNumber))
            }

            SignWarningBox(warningText = phoneNumberError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.user_name),
                content = viewModel.username
            ) { username ->
                viewModel.onEvent(AuthEvent.ChangeUserName(username))
            }

            SignWarningBox(warningText = usernameError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.password),
                content = viewModel.password
            ) { password ->
                viewModel.onEvent(AuthEvent.ChangePassword(password))
            }

            SignWarningBox(warningText = passwordError)

            Spacer(modifier = Modifier.height(smallPadding))
            
            
            ConfirmButton(
                modifier = Modifier,
                text = stringResource(id = R.string.confirm),
                isEnglish = isEnglish
            ) {
                viewModel.signupLandowner()
            }

            Spacer(modifier = Modifier.height(largePadding))

            LotusRow(
                highlightedLotusPos = 0,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(largePadding))
        }
    }

}

@Preview
@Composable
fun LandownerSignupPreview() {
    LandownerSignup(navController = rememberNavController())
}