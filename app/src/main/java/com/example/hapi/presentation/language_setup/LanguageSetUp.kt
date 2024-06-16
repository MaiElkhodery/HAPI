package com.example.hapi.presentation.language_setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.ContinueButtonWithYellowBackground
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.presentation.welcome.navigateToWelcomeScreen
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun LanguageSetUp(
    navController: NavController,
    onLanguageSelected: (String) -> Unit = {},
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val appLanguage by languageViewModel.appLanguage.collectAsState()

    LaunchedEffect(appLanguage) {
        languageViewModel.getLanguage()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenHeight = maxHeight

        val topPadding = screenHeight * 0.08f
        val contentMargin = if (screenHeight < 600.dp) 12.dp else 18.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(topPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(contentMargin))

            LanguageOptions(
                isEnglishSelected = appLanguage == ENGLISH
            ) { language ->
                onLanguageSelected(language)
                languageViewModel.getLanguage()
            }

            ContinueButtonWithYellowBackground {
               navController.navigateToWelcomeScreen()
            }

            Spacer(modifier = Modifier.height(contentMargin))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.main_crop),
                    contentScale = ContentScale.Fit,
                    contentDescription = "crop"
                )
            }
        }
    }
}

@Preview
@Composable
private fun LanguageSetUpPreview() {
    LanguageSetUp(navController = rememberNavController())
}