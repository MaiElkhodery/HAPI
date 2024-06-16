package com.example.hapi.presentation.auth.signup.farmersignup

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
import com.example.hapi.presentation.auth.viewmodel.AuthEvent
import com.example.hapi.presentation.auth.viewmodel.AuthViewModel
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.SignLabeledInputFields
import com.example.hapi.presentation.common.SignWarningBox
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun FarmerSignup(
    navController: NavController,
    languageViewModel: LanguageViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val phoneNumberError = authViewModel.phoneNumberError.collectAsState().value
    val usernameError = authViewModel.usernameError.collectAsState().value
    val passwordError = authViewModel.passwordError.collectAsState().value
    val farmIdError = authViewModel.landIdError.collectAsState().value
    val authenticated = authViewModel.authenticated.collectAsState().value
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    val scrollState = rememberScrollState()

    LaunchedEffect(authenticated) {
        if (authenticated) {
            navController.navToProgress("true", isFarmer = "true")
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
        val horizontalPadding = if (screenWidth < 400.dp) 24.dp else 28.dp


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
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize
            ) {
                navController.navigateToIdentitySelection()
            }

            Spacer(modifier = Modifier.height(largePadding))

            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {

                SignLabeledInputFields(
                    title = stringResource(id = R.string.phone_number),
                    content = authViewModel.phoneNumber
                ) { phoneNumber ->
                    authViewModel.onEvent(AuthEvent.ChangePhoneNumber(phoneNumber))
                }

                SignWarningBox(warningText = phoneNumberError)

                SignLabeledInputFields(
                    title = stringResource(id = R.string.user_name),
                    content = authViewModel.username
                ) { username ->
                    authViewModel.onEvent(AuthEvent.ChangeUserName(username))
                }

                SignWarningBox(warningText = usernameError)

                SignLabeledInputFields(
                    title = stringResource(id = R.string.password),
                    content = authViewModel.password
                ) { password ->
                    authViewModel.onEvent(AuthEvent.ChangePassword(password))
                }

                SignWarningBox(warningText = passwordError)

                SignLabeledInputFields(
                    title = stringResource(id = R.string.farm_id),
                    content = authViewModel.landId
                ) { farmId ->
                    authViewModel.onEvent(AuthEvent.ChangeFarmId(farmId))
                }

                SignWarningBox(warningText = farmIdError)

            }
            Spacer(modifier = Modifier.height(largePadding))

            ConfirmButton(
                isEnglish = isEnglish,
                modifier = Modifier,
                text = stringResource(id = R.string.confirm_signup)
            ) {
                authViewModel.signupFarmer()
            }
            Spacer(modifier = Modifier.height(largePadding))

        }

    }

}

@Preview
@Composable
private fun FarmerSignupPreview() {
    FarmerSignup(navController = rememberNavController())
}