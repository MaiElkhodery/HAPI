package com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signup.landownersignup.cropselection.navigateToSignupCropSelection
import com.example.hapi.presentation.auth.signup.landownersignup.recommendedcrops.navigateToRecommendedCrops
import com.example.hapi.presentation.auth.viewmodel.AuthViewModel
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.LotusRow
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun CropSelectionStrategy(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val crops = viewModel.recommendedCrops.collectAsState().value
    val error = viewModel.errorMsg.collectAsState().value
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    LaunchedEffect(crops) {
        if (crops.isNotEmpty()) {
            navController.navigateToRecommendedCrops(crops.joinToString(","))
        }
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxWidth

        val padding = screenHeight * 0.034f
        val logoSize = if (screenHeight < 650.dp) 55.dp else 75.dp
        val backIconSize = if (screenHeight < 600.dp) 60 else 75
        val fontSize = when {
            screenWidth < 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(padding))
            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)

            )
            Spacer(modifier = Modifier.height(padding))
            NavHeader(
                modifier = Modifier,
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize,
                fontSize = fontSize
            ) {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(padding))

            CropStrategyOptions(
                width = screenWidth,
                onClickRecommendation = {
                    viewModel.getRecommendedCrops()
                },
                onClickHaveCrop = {
                    navController.navigateToSignupCropSelection()
                }
            )

            Spacer(modifier = Modifier.height(padding))

            LotusRow(highlightedLotusPos = 1)

            Spacer(modifier = Modifier.height(padding))

        }
    }
}

@Preview
@Composable
private fun CropRecommendationPreview() {
    CropSelectionStrategy(rememberNavController())
}