package com.example.hapi.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signin.navToSignin
import com.example.hapi.presentation.common.TextWithIcon
import com.example.hapi.presentation.detection.guest_cropselection.navigateToGuestCropSelection
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun WelcomeScreen(
    navController: NavController,
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val largePadding = screenHeight * .04f
        val smallPadding = screenHeight * .025f
        val horizontalPadding = 44.dp
        val iconSize = when {
            screenWidth < 360.dp -> 18.dp
            else -> 24.dp
        }
        val imageSize = when {
            screenHeight <= 600.dp -> 130.dp
            screenHeight in 600.dp..855.dp -> 185.dp
            else -> 200.dp
        }
        val fontSize = when {
            screenWidth < 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(largePadding))

            Image(
                modifier = Modifier.size(imageSize),
                painter = painterResource(id = R.drawable.welcom_to_hapi),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(largePadding))

            SignOption(
                text = stringResource(id = R.string.first_time),
                buttonText = stringResource(id = R.string.signup),
                fontSize = fontSize,
                iconSize = iconSize,
                isEnglish = isEnglish
            ) {
                navController.navToProgress("false")
            }

            Spacer(modifier = Modifier.height(smallPadding))

            SignOption(
                text = stringResource(id = R.string.have_account),
                buttonText = stringResource(id = R.string.signin),
                fontSize = fontSize,
                iconSize = iconSize,
                isEnglish = isEnglish
            ) {
                navController.navToSignin()
            }

            Spacer(modifier = Modifier.height(largePadding))

            TextWithIcon(
                text = stringResource(id = R.string.detect_now),
                fontSize = fontSize,
                iconId =
                if (isEnglish) R.drawable.continue_icon
                else R.drawable.continue_icon_ar
            ) {
                navController.navigateToGuestCropSelection()
            }

            SetUpBottomImage(Modifier.padding(top = 10.dp))
        }

    }
}


@Preview
@Composable
fun MainPreview() {
    WelcomeScreen(navController = rememberNavController())
}