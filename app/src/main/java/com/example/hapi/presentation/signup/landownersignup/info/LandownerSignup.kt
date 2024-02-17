package com.example.hapi.presentation.signup.landownersignup.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hapi.R
import com.example.hapi.presentation.signup.common.ConfirmButton
import com.example.hapi.presentation.signup.common.LabeledInputField
import com.example.hapi.presentation.signup.common.Logo
import com.example.hapi.presentation.signup.common.SignupAndGuestHeader
import com.example.hapi.presentation.signup.common.WarningBox
import com.example.hapi.presentation.signup.farmersignup.viewmodel.SignupEvent
import com.example.hapi.presentation.signup.landownersignup.detection.navToCropDetection
import com.example.hapi.presentation.signup.landownersignup.viewmodel.LandownerViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun LandownerSignup(
    navController: NavController,
    viewModel: LandownerViewModel = hiltViewModel()
) {

    val phoneNumberError = viewModel.phoneNumberError.collectAsState().value
    val usernameError = viewModel.usernameError.collectAsState().value
    val passwordError = viewModel.passwordError.collectAsState().value
    val authenticated = viewModel.authenticated.collectAsState().value

    LaunchedEffect(authenticated) {
        if (authenticated) {
            navController.navToCropDetection()
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (logo, header, content, button, lotusRow) = createRefs()
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
        SignupAndGuestHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(logo.bottom, margin = Dimens.header_margin)
                bottom.linkTo(content.top,margin = Dimens.header_margin)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        ) {
            navController.popBackStack()
        }

        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .constrainAs(content) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(button.top)
                },
            verticalArrangement = Arrangement.Center
        ) {

            LabeledInputField(
                title = stringResource(id = R.string.phone_number),
                content = viewModel.phoneNumber
            ) { phoneNumber ->
                viewModel.onEvent(SignupEvent.ChangePhoneNumber(phoneNumber))
            }

            WarningBox(warningText = phoneNumberError)

            LabeledInputField(
                title = stringResource(id = R.string.user_name),
                content = viewModel.username
            ) { username ->
                viewModel.onEvent(SignupEvent.ChangeUsrName(username))
            }

            WarningBox(warningText = usernameError)

            LabeledInputField(
                title = stringResource(id = R.string.password),
                content = viewModel.password
            ) { password ->
                viewModel.onEvent(SignupEvent.ChangePassword(password))
            }

            WarningBox(warningText = passwordError)

        }

        ConfirmButton(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(content.bottom)
                    bottom.linkTo(lotusRow.top)
                },
            text = stringResource(id = R.string.confirm)
        ) {
            viewModel.signup()
        }

        LotusRow(
            highlightedLotusPos = 0,
            modifier = Modifier
                .constrainAs(lotusRow) {
                    top.linkTo(button.bottom, margin = 30.dp)
                    bottom.linkTo(bottomGuideLine)
                }
        )
    }
}

@Preview
@Composable
fun LandownerSignupPreview() {
//    LandownerSignup(rememberNavController(), LandownerViewModel())
}