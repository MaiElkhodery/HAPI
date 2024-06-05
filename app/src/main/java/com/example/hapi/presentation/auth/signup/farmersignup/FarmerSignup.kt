package com.example.hapi.presentation.auth.signup.farmersignup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.SignLabeledInputFields
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.WarningBox
import com.example.hapi.presentation.auth.viewmodel.AuthEvent
import com.example.hapi.presentation.auth.viewmodel.AuthViewModel
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
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
    val isEnglish = languageViewModel.appLanguage.collectAsState().value== ENGLISH

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {
        val (logo, header, content, button) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_sign)
        val bottomGuideLine = createGuidelineFromBottom(Dimens.bottom_guideline_sign)

        Logo(
            modifier = Modifier
                .fillMaxWidth()
                .size(70.dp)
                .constrainAs(logo) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(header.top)
                }
        )
        NavHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(logo.bottom, margin = Dimens.header_margin)
                    bottom.linkTo(content.top, margin = Dimens.header_margin)
                },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account),
            imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar
        ) {
            navController.navigateToIdentitySelection()
        }

        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .constrainAs(content) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(button.top)
                },
            verticalArrangement = Arrangement.Center
        ) {

            SignLabeledInputFields(
                title = stringResource(id = R.string.phone_number),
                content = authViewModel.phoneNumber
            ) { phoneNumber ->
                authViewModel.onEvent(AuthEvent.ChangePhoneNumber(phoneNumber))
            }

            WarningBox(warningText = phoneNumberError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.user_name),
                content = authViewModel.username
            ) { username ->
                authViewModel.onEvent(AuthEvent.ChangeUserName(username))
            }

            WarningBox(warningText = usernameError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.password),
                content = authViewModel.password
            ) { password ->
                authViewModel.onEvent(AuthEvent.ChangePassword(password))
            }

            WarningBox(warningText = passwordError)

            SignLabeledInputFields(
                title = stringResource(id = R.string.farm_id),
                content = authViewModel.landId
            ) { farmId ->
                authViewModel.onEvent(AuthEvent.ChangeFarmId(farmId))
            }

            WarningBox(warningText = farmIdError)

        }

        ConfirmButton(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(content.bottom)
                    bottom.linkTo(bottomGuideLine)
                },
            text = stringResource(id = R.string.confirm_signup)
        ) {
            authViewModel.signupFarmer()
        }
    }
    if (authenticated) {
        navController.navToProgress("true", isFarmer = "true")
    }
}

@Preview
@Composable
private fun FarmerSignupPreview() {
    FarmerSignup(navController = rememberNavController())
}