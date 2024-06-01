package com.example.hapi.presentation.settings.support

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
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
    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_settings_options)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = R.string.help_and),
            downText = stringResource(id = R.string.support),
            imageId = if (isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }

        HelpAndSupportContent(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                    centerHorizontallyTo(parent)
                    centerVerticallyTo(parent)
                },
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
    }
}

@Preview
@Composable
private fun HelpAndSupportPreview() {
    HelpAndSupport(navController = rememberNavController())
}