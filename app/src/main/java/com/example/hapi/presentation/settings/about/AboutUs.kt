package com.example.hapi.presentation.settings.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.presentation.settings.support.ButtonWithEndIcon
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ABOUT_US_LINK
import com.example.hapi.util.Dimens
import com.example.hapi.util.Tab
import com.example.hapi.util.YellowBlackText

@Composable
fun AboutUs(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(bottom = 36.dp) // Increased bottom padding
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val horizontalPadding = if (screenWidth < 360.dp) 16.dp else 22.dp
        val verticalMargin = if (screenHeight < 600.dp) 16.dp else 24.dp // Reduced vertical margin

        Column(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            NavHeader(
                modifier = Modifier
                    .padding(top = Dimens.small_horizontal_padding)
                    .fillMaxWidth(),
                topText = stringResource(id = R.string.about),
                downText = stringResource(id = R.string.us),
                imageId = if (isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar
            ) {
                mainViewModel.setSelectedTab(Tab.SETTINGS)
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(verticalMargin)) // Space between header and image

            Image(
                modifier = Modifier.size(if (screenWidth < 360.dp) 120.dp else 180.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(verticalMargin)) // Space between image and text

            YellowBlackText(
                size = 10,
                text = stringResource(id = R.string.about_us_text),
                modifier = Modifier
                    .padding(
                        vertical = 12.dp, // Reduced vertical padding
                        horizontal = if (screenWidth < 360.dp) 30.dp else 55.dp
                    )
            )

            ButtonWithEndIcon(
                text = stringResource(id = R.string.learn_more),
                imageId = R.drawable.learn_more_icon,
                modifier = Modifier.padding(horizontal = 70.dp)
            ) {
                ContextCompat.startActivity(
                    context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(ABOUT_US_LINK)),
                    null
                )
            }

            Spacer(modifier = Modifier.height(verticalMargin * 2)) // Extra space at the bottom
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