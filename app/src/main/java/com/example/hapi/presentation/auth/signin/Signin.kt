package com.example.hapi.presentation.auth.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.hapi.presentation.auth.common.ConfirmButton
import com.example.hapi.presentation.auth.common.LabeledInputField
import com.example.hapi.presentation.auth.common.Logo
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.auth.common.WarningBox
import com.example.hapi.presentation.auth.signup.landownersignup.info.LotusRow
import com.example.hapi.presentation.auth.viewmodel.AuthEvent
import com.example.hapi.presentation.auth.viewmodel.AuthViewModel
import com.example.hapi.presentation.home.landowner.navigateToLandownerHome
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens

@Composable
fun Signin(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val errorMsg = viewModel.errorMsg.collectAsState().value
    val authenticated = viewModel.authenticated.collectAsState().value
    val isLandowner = viewModel.isLandowner.collectAsState().value

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
        NavHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(logo.bottom, margin = Dimens.header_margin)
                bottom.linkTo(content.top)
            },
            topText = stringResource(id = R.string.sign),
            downText = stringResource(id = R.string._in)
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

            WarningBox(warningText = errorMsg)

            LabeledInputField(
                title = stringResource(id = R.string.phone_number),
                content = viewModel.phoneNumber
            ) { phone_number ->
                viewModel.onEvent(AuthEvent.ChangePhoneNumber(phone_number))
            }
            Spacer(modifier = Modifier.padding(12.dp))

            LabeledInputField(
                title = stringResource(id = R.string.password),
                content = viewModel.password
            ) { password ->
                viewModel.onEvent(AuthEvent.ChangePassword(password))
            }


        }

        ConfirmButton(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(content.bottom)
                    bottom.linkTo(lotusRow.top)
                },
            text = stringResource(id = R.string.signin)
        ) {
            Log.d("SIGNIN", "SIGNIN")
            viewModel.signin()
            navController.navigateToLandownerHome()
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
    if (authenticated) {
        if (isLandowner) {
            navController.navigateToLandownerHome()
        } else {
            //TODO: nav to farmer home
        }
    }
}

@Preview
@Composable
fun SigninPreview() {
    Signin(rememberNavController())
}