package com.example.hapi.presentation.settings.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.Tab

@Composable
fun LanguageSettings(
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController

) {
    val isEnglishIsSelected = languageViewModel.isEnglishIsSelected.collectAsState().value

    LaunchedEffect(isEnglishIsSelected) {
        languageViewModel.getLanguage()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_settings_options)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = if(isEnglishIsSelected) R.string.language else R.string.settings),
            downText = stringResource(id = if(isEnglishIsSelected) R.string.settings else R.string.language),
            imageId = if (isEnglishIsSelected) R.drawable.settings_back_btn
            else R.drawable.settings_back_btn_ar,
            imageSize = 80
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(content) {
                    top.linkTo(navHeader.bottom)
                    bottom.linkTo(parent.bottom)
                },
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
                    modifier = Modifier.size(130.dp)
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
            )

            LanguageOption(
                isSelected = !isEnglishIsSelected,
                textId = R.string.arabic,
                selectedColor = YellowAppColor,
                unselectedColor = DarkGreenAppColor,
                selectedFontSize = 24,
                unselectedFontSize = 20
            )

        }
    }
}

@Preview
@Composable
fun LanguageSettingsPreview() {
    LanguageSettings(navController = rememberNavController())
}