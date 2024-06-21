package com.example.hapi.presentation.language_setup

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.NextButton
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.presentation.welcome.SetUpBottomImage
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
        val screenWidth = maxWidth

        val smallPadding = screenHeight * 0.04f
        val largePadding = screenHeight * 0.05f
        val logoSize = when {
            screenHeight <= 600.dp -> 130.dp
            screenHeight in 600.dp..855.dp -> 185.dp
            else -> 200.dp
        }
        val iconSize = when {
            screenWidth <= 360.dp -> 27.dp
            else -> 35.dp
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(largePadding))

            Logo(modifier = Modifier.size(logoSize))

            Spacer(modifier = Modifier.height(largePadding))

            LanguageOptions(
                width = screenWidth,
                isEnglishSelected = appLanguage == ENGLISH
            ) { language ->
                onLanguageSelected(language)
                languageViewModel.getLanguage()
            }

            Spacer(modifier = Modifier.height(smallPadding))

            NextButton(iconSize) {
                navController.navigateToWelcomeScreen()
            }

            Spacer(modifier = Modifier.height(smallPadding))

            SetUpBottomImage()

        }
    }
}

@Preview
@Composable
private fun LanguageSetUpPreview() {
    LanguageSetUp(navController = rememberNavController())
}