package com.example.hapi.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.hapi.presentation.detection.guest_cropselection.navigateToGuestCropSelection
import com.example.hapi.presentation.home.common.ORTextRow
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
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

        val verticalPadding = if (screenHeight < 600.dp) 33.dp else 44.dp
        val horizontalPadding = if (screenWidth < 360.dp) 16.dp else 44.dp
        val contentMargin = if (screenHeight < 600.dp) 8.dp else Dimens.content_margin

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier
                    .padding(top = verticalPadding)
                    .weight(1f, fill = false),
                painter = painterResource(id = R.drawable.welcom_to_hapi),
                contentDescription = null
            )

            WelcomeScreenContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = contentMargin),
                text = stringResource(id = R.string.first_time),
                buttonText = stringResource(id = R.string.signup),
                isEnglish = isEnglish
            ) {
                navController.navToProgress("false")
            }

            WelcomeScreenContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = contentMargin),
                text = stringResource(id = R.string.have_account),
                buttonText = stringResource(id = R.string.signin),
                isEnglish = isEnglish
            ) {
                navController.navToSignin()
            }

            ORTextRow(
                modifier = Modifier
                    .padding(top = contentMargin, bottom = contentMargin),
                text = stringResource(id = R.string.detect_now),
                iconId = if (isEnglish) R.drawable.continue_icon else R.drawable.continue_icon_ar
            ) {
                navController.navigateToGuestCropSelection()
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    painter = painterResource(id = R.drawable.main_crop),
                    contentDescription = null
                )
            }
        }

    }
}


@Preview
@Composable
fun MainPreview() {
    WelcomeScreen(navController = rememberNavController())
}