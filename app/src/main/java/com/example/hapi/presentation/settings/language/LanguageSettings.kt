package com.example.hapi.presentation.settings.language

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.ARABIC
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.Tab

@Composable
fun LanguageSettings(
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController,
    onLanguageSelected: (String) -> Unit = {}

) {
    val isEnglishIsSelected = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    LaunchedEffect(isEnglishIsSelected) {
        languageViewModel.getLanguage()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val horizontalPadding = if (screenWidth < 360.dp) 12.dp else 16.dp
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val padding = screenHeight * 0.03f
        val headerFontSize = when {
            screenWidth <= 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }
        val imageSize = when {
            screenHeight <= 600.dp -> 100.dp
            screenHeight in 600.dp..855.dp -> 120.dp
            else -> 150.dp
        }

        Column(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHeader(
                modifier = Modifier.padding(vertical = 12.dp),
                topText = stringResource(id = if (isEnglishIsSelected) R.string.language else R.string.settings),
                downText = stringResource(id = if (isEnglishIsSelected) R.string.settings else R.string.language),
                imageId = if (isEnglishIsSelected) R.drawable.settings_back_btn
                else R.drawable.settings_back_btn_ar,
                imageSize = backIconSize,
                fontSize = headerFontSize
            ) {
                mainViewModel.setSelectedTab(Tab.SETTINGS)
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(padding))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.language_icon),
                        contentDescription = null,
                        tint = YellowAppColor,
                        modifier = Modifier.size(imageSize)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                LanguageOption(
                    isSelected = isEnglishIsSelected,
                    textId = R.string.english,
                    selectedColor = YellowAppColor,
                    unselectedColor = DarkGreenAppColor,
                    selectedFontSize = 24,
                    unselectedFontSize = 20
                ) {
                    onLanguageSelected(ENGLISH)
                }

                LanguageOption(
                    isSelected = !isEnglishIsSelected,
                    textId = R.string.arabic,
                    selectedColor = YellowAppColor,
                    unselectedColor = DarkGreenAppColor,
                    selectedFontSize = 24,
                    unselectedFontSize = 20
                ) {
                    onLanguageSelected(ARABIC)
                }

                Spacer(modifier = Modifier.height(padding))
            }
        }
    }
}

@Preview
@Composable
fun LanguageSettingsPreview() {
    LanguageSettings(navController = rememberNavController())
}