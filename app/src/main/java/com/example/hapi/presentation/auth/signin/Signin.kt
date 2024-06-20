package com.example.hapi.presentation.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.hapi.presentation.common.AuthWarningBox
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.LabeledInputFields
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.common.YellowRegularText
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.main.navigateToMainScreen
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.FARMER
import com.example.hapi.util.LANDOWNER
import com.example.hapi.util.ScreenSize
import com.example.hapi.util.Tab
import com.example.hapi.util.getScreenWidth

@Composable
fun Signin(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    signinViewModel: AuthViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val errorMsg = signinViewModel.errorMsg.collectAsState().value
    val authenticated = signinViewModel.authenticated.collectAsState().value
    val isLandowner = signinViewModel.isLandowner.collectAsState().value
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    LaunchedEffect(authenticated) {
        if (authenticated) {
            mainViewModel.setSelectedTab(Tab.HOME)
            if (isLandowner) {
                navController.navigateToMainScreen(
                    role = LANDOWNER
                )
            } else {
                navController.navigateToMainScreen(
                    role = FARMER
                )
            }
        }
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxWidth

        val largePadding = screenHeight * 0.1f
        val smallPadding = screenHeight * 0.05f
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
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(largePadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )

            NavHeader(
                modifier = Modifier.padding(bottom = 5.dp),
                topText = stringResource(id = R.string.sign),
                downText = stringResource(id = R.string._in),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize,
                fontSize = fontSize
            ) {
                navController.popBackStack()
            }


            AuthWarningBox(
                width = screenWidth,
                warningText = errorMsg
            )

            LabeledInputFields(
                height=screenHeight,
                width = screenWidth,
                title = stringResource(id = R.string.phone_number),
                content = signinViewModel.phoneNumber
            ) { phoneNumber ->
                signinViewModel.onEvent(AuthEvent.ChangePhoneNumber(phoneNumber))
            }

            Spacer(modifier = Modifier.padding(12.dp))

            LabeledInputFields(
                height=screenHeight,
                width = screenWidth,
                title = stringResource(id = R.string.password),
                content = signinViewModel.password
            ) { password ->
                signinViewModel.onEvent(AuthEvent.ChangePassword(password))
            }

            Spacer(modifier = Modifier.height(smallPadding))

            ConfirmButton(
                width = screenWidth,
                isEnglish = isEnglish,
                text = stringResource(id = R.string.signin)
            ) {
                signinViewModel.signin()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                YellowRegularText(
                    text = stringResource(id = R.string.have_not_account),
                    size = fontSize
                )
                YellowBlackText(text = stringResource(id = R.string.signup),
                    size = fontSize,
                    modifier = Modifier.clickable {
                        navController.navigateToIdentitySelection()
                    }
                )
            }

            Spacer(modifier = Modifier.height(largePadding))
        }
    }

}

@Preview
@Composable
fun SigninPreview() {
    Signin(rememberNavController())
}