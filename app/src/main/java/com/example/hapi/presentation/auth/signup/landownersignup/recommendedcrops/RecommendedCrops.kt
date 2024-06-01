package com.example.hapi.presentation.auth.signup.landownersignup.recommendedcrops

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signup.landownersignup.finalcrop.navigateToFinalSelectedCrop
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.navigateToCropSelectionStrategy
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.LotusRow
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.Title
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Crop

@Composable
fun RecommendedCrops(
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController,
    crops: String,
) {

    val topRecommendedCropsList = crops.split(",").map { Crop.valueOf(it) }
    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(horizontal = 26.dp)
    ) {

        val screenHeight = maxHeight

        val topPadding = screenHeight * 0.02f
        val contentPadding = screenHeight * 0.03f
        val bottomPadding = screenHeight * 0.04f
        val logoSize = if (screenHeight < 600.dp) 60.dp else 90.dp
        val backIconSize = if (screenHeight < 600.dp) 60 else 80

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(topPadding))
            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )
            Spacer(modifier = Modifier.height(contentPadding))
            NavHeader(
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize
            ) {
                navController.navigateToCropSelectionStrategy()
            }
            Spacer(modifier = Modifier.height(contentPadding))

            Title(title = stringResource(id = R.string.choose_recommedation))
            Spacer(modifier = Modifier.height(contentPadding))

            RecommendedCropsList(
                topCrops = topRecommendedCropsList
            ) { crop ->
                navController.navigateToFinalSelectedCrop(crop.name)
            }
            Spacer(modifier = Modifier.height(bottomPadding))

            LotusRow(
                highlightedLotusPos = 2,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(bottomPadding))

        }
    }
}

@Preview
@Composable
private fun FinalCropScreenPreview() {
    RecommendedCrops(
        navController = rememberNavController(),
        crops = "Crops"
    )
}