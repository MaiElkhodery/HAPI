package com.example.hapi.presentation.signup.landownersignup.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.signup.farmersignup.ui.ConfirmButton
import com.example.hapi.presentation.signup.farmersignup.ui.FarmerSignupHeader
import com.example.hapi.presentation.signup.farmersignup.ui.LabeledInputField
import com.example.hapi.presentation.signup.farmersignup.ui.WarningBox
import com.example.hapi.presentation.signup.farmersignup.viewmodel.SignupEvent
import com.example.hapi.presentation.signup.landownersignup.viewmodel.LandownerViewModel
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun LandownerSignup(
    navController: NavController,
    viewModel: LandownerViewModel = hiltViewModel()
) {
    val phoneNumberError = viewModel.phoneNumberError.collectAsState().value
    val usernameError = viewModel.usernameError.collectAsState().value
    val passwordError = viewModel.passwordError.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val (header, content, button, lotusRow) = createRefs()
        val topGuideLine = createGuidelineFromTop(.08f)
        val bottomGuideLine = createGuidelineFromBottom(.06f)

        FarmerSignupHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(topGuideLine)
            }
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

        }

        ConfirmButton(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(content.bottom)
                },
            text = stringResource(id = R.string.confirm_signup)
        ) {
            //TODO: SIGNUP AND IF IT IS IS SUCCESSFUL NAV TO CONGRATULATIONS SCREEN
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(lotusRow) {
                    top.linkTo(button.bottom)
                    bottom.linkTo(bottomGuideLine)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(1.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.top_lotus),
                contentDescription = null,
            )
            repeat(3) {
                Image(
                    modifier = Modifier
                        .padding(1.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.lotus2),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
fun LandownerSignupPreview() {
    LandownerSignup(rememberNavController(), LandownerViewModel())
}