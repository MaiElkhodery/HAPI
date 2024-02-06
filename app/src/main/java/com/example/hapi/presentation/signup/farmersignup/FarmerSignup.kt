package com.example.hapi.presentation.signup.farmersignup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun FarmerSignup(
    navController: NavController,
    viewModel: FarmerSignupViewModel = hiltViewModel()
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 16.dp)
    ) {
        val (header, content, button) = createRefs()
        val topGuideLine = createGuidelineFromTop(.06f)
        val bottomGuideLine = createGuidelineFromBottom(.06f)

        FarmerSignupHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(topGuideLine)
            }
        )

        Column(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(header.bottom)
                },
            verticalArrangement = Arrangement.Center
        ) {
            LabeledInputField(
                title = stringResource(id = R.string.phone_number),
                content = viewModel.phoneNumber
            ) {

            }
            LabeledInputField(
                title = stringResource(id = R.string.user_name),
                content = viewModel.username
            ) {

            }
            LabeledInputField(
                title = stringResource(id = R.string.password),
                content = viewModel.phoneNumber
            ) {

            }
            LabeledInputField(
                title = stringResource(id = R.string.farm_id),
                content = viewModel.farmId
            ) {

            }
        }

        ConfirmButton(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(content.bottom)
                bottom.linkTo(bottomGuideLine)
            },
            text = stringResource(id = R.string.confirm_signup)
        ) {

        }
    }
}

@Preview
@Composable
private fun FarmerSignupPreview() {
    FarmerSignup(navController = rememberNavController())
}