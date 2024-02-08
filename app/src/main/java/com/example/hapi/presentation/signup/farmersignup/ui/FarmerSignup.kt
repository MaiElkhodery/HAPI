package com.example.hapi.presentation.signup.farmersignup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.hapi.presentation.signup.common.ConfirmButton
import com.example.hapi.presentation.signup.common.LabeledInputField
import com.example.hapi.presentation.signup.common.SignupAndGuestHeader
import com.example.hapi.presentation.signup.common.WarningBox
import com.example.hapi.presentation.signup.farmersignup.viewmodel.SignupEvent
import com.example.hapi.presentation.signup.farmersignup.viewmodel.FarmerSignupViewModel
import com.example.hapi.presentation.signup.progress.navigateToProgress
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun FarmerSignup(
    navController: NavController,
    viewModel: FarmerSignupViewModel = hiltViewModel()
) {

    val phoneNumberError = viewModel.phoneNumberError.collectAsState().value
    val usernameError = viewModel.usernameError.collectAsState().value
    val passwordError = viewModel.passwordError.collectAsState().value
    val farmIdError = viewModel.farmIdError.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {
        val (header, content, button) = createRefs()
        val topGuideLine = createGuidelineFromTop(.08f)
        val bottomGuideLine = createGuidelineFromBottom(.06f)

        SignupAndGuestHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(topGuideLine)
            },
            topText = stringResource(id = R.string.setting_up),
            downText = stringResource(id = R.string.your_account)
        )

        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
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

            LabeledInputField(
                title = stringResource(id = R.string.farm_id),
                content = viewModel.farmId
            ) { farmId ->
                viewModel.onEvent(SignupEvent.ChangeFarmId(farmId))
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
            //TODO: SIGNUP AND IF IT IS IS SUCCESSFUL NAV TO CONGRATULATIONS SCREEN
            navController.navigateToProgress(isFinal = true)
        }
    }
}

@Preview
@Composable
private fun FarmerSignupPreview() {
    FarmerSignup(navController = rememberNavController(), FarmerSignupViewModel())
}