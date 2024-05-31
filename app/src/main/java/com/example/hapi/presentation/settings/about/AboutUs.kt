package com.example.hapi.presentation.settings.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(bottom = 26.dp)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_settings_options)
        val bottomGuideLine = createGuidelineFromBottom(Dimens.top_guideline_settings_options)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = R.string.about),
            downText = stringResource(id = R.string.us),
            imageId = if (isEnglish) R.drawable.settings_back_btn
            else R.drawable.settings_back_btn_ar
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                    bottom.linkTo(bottomGuideLine)
                    centerVerticallyTo(parent)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(180.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            YellowBlackText(
                size = 10,
                text = stringResource(id = R.string.about_us_text),
                modifier = Modifier
                    .padding(vertical = 26.dp, horizontal = 55.dp)
            )

            ButtonWithEndIcon(
                text = stringResource(id = R.string.learn_more),
                imageId = R.drawable.learn_more_icon,
                modifier = Modifier.padding(horizontal = 60.dp)
            ) {
                ContextCompat.startActivity(
                    context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(ABOUT_US_LINK)),
                    null
                )
            }
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