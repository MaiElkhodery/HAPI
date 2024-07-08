package com.example.hapi.presentation.settings.about

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.presentation.settings.support.ButtonWithEndIcon
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ABOUT_US_LINK
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.Tab

@Composable
fun AboutUs(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val horizontalPadding = if (screenWidth < 360.dp) 12.dp else 16.dp

        val largePadding = screenHeight * 0.04f
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val headerFontSize = when {
            screenWidth < 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }
        val textFontSize = when {
            screenWidth < 360.dp -> 8
            screenWidth in 360.dp..400.dp -> 10
            else -> 12
        }
        val btnFontSize = when {
            screenWidth < 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 14
            else -> 16
        }
        val logoSize = when {
            screenHeight <= 600.dp -> 120.dp
            screenHeight in 600.dp..855.dp -> 150.dp
            else -> 180.dp
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            NavHeader(
                modifier = Modifier.padding(vertical = 12.dp),
                topText = stringResource(id = R.string.about),
                downText = stringResource(id = R.string.us),
                imageId = if (isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar,
                fontSize = headerFontSize,
                imageSize = backIconSize
            ) {
                mainViewModel.setSelectedTab(Tab.SETTINGS)
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(largePadding))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Logo(modifier = Modifier.size(logoSize))

                Spacer(modifier = Modifier.height(largePadding))

                YellowBlackText(
                    modifier = Modifier.padding(horizontal = screenWidth * .1f),
                    size = textFontSize,
                    text = stringResource(id = R.string.about_us_text),
                )

                Spacer(modifier = Modifier.height(largePadding))

                ButtonWithEndIcon(
                    text = stringResource(id = R.string.learn_more),
                    imageId = R.drawable.learn_more_icon,
                    fontSize = btnFontSize,
                    modifier = Modifier.padding(horizontal = screenWidth * .25f)
                ) {
                    ContextCompat.startActivity(
                        context,
                        Intent(Intent.ACTION_VIEW, Uri.parse(ABOUT_US_LINK)),
                        null
                    )
                }
            }
            Spacer(modifier = Modifier.height(largePadding))
        }
    }
}

@Preview
@Composable
fun AboutUsPreview() {
    AboutUs(
        navController = rememberNavController()
    )
}