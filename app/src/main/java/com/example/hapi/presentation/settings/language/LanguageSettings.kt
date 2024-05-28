package com.example.hapi.presentation.settings.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(bottom = 26.dp)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(.05f)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = R.string.data_storage),
            downText = stringResource(id = R.string.settings),
            imageId = R.drawable.settings_back_btn
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
                }
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
            Text(
                color =
                if (isEnglishIsSelected) YellowAppColor
                else DarkGreenAppColor,
                fontSize = if (isEnglishIsSelected) 24.sp else 20.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_black
                    )
                ),
                text = stringResource(id = R.string.english),
                textAlign = TextAlign.Center
            )

            Text(
                color =
                if (isEnglishIsSelected) DarkGreenAppColor
                else YellowAppColor,
                fontSize = if (isEnglishIsSelected) 20.sp else 24.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_black
                    )
                ),
                text = stringResource(id = R.string.arabic),
                textAlign = TextAlign.Center
            )

        }
    }
}

@Preview
@Composable
fun LanguageSettingsPreview() {
    LanguageSettings(navController = rememberNavController())
}