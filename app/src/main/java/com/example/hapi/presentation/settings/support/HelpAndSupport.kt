package com.example.hapi.presentation.settings.support

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.FQA_LINK
import com.example.hapi.util.PHONE_NUMBER
import com.example.hapi.util.Tab

@Composable
fun HelpAndSupport(
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
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val padding = screenHeight * 0.03f
        val headerFontSize = when {
            screenWidth < 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }

        Column(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NavHeader(
                modifier = Modifier.padding(vertical = 12.dp),
                topText = stringResource(id = R.string.help_and),
                downText = stringResource(id = R.string.support),
                imageId = if (isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar,
                imageSize = backIconSize,
                fontSize = headerFontSize
            ) {
                mainViewModel.setSelectedTab(Tab.SETTINGS)
                navController.popBackStack()
            }

            HelpAndSupportOptions(
                width = screenWidth,
                height = screenHeight,
                onFQAClick = {
                    startActivity(
                        context,
                        Intent(Intent.ACTION_VIEW, Uri.parse(FQA_LINK)),
                        null
                    )
                },
                onCallNowClick = {
                    startActivity(
                        context,
                        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$PHONE_NUMBER")),
                        null
                    )
                }
            )

            Spacer(modifier = Modifier.height(padding))
        }
    }
}

@Preview
@Composable
private fun HelpAndSupportPreview() {
    HelpAndSupport(navController = rememberNavController())
}