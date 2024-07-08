package com.example.hapi.presentation.auth.signup.landownersignup.finalcrop

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
import com.example.hapi.presentation.auth.viewmodel.AuthViewModel
import com.example.hapi.presentation.common.Logo
import com.example.hapi.presentation.common.LotusRow
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.common.getCrop
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun FinalSelectedCrop(
    navController: NavController,
    cropId: Int,
    viewModel: AuthViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val error = viewModel.errorMsg.collectAsState().value
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    val cropIsUploaded = viewModel.cropIsUploaded.collectAsState().value

    val selectedCrop = getCrop(cropId = cropId)

    LaunchedEffect(error, cropIsUploaded) {
        if (error.isNotEmpty()) {
            //TODO: handle error
        }
        if (cropIsUploaded) {
            navController.navToProgress(final = "true")
        }
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxWidth

        val smallPadding = screenHeight * 0.034f
        val largePadding = screenHeight * 0.044f
        val logoSize = if (screenHeight < 650.dp) 55.dp else 75.dp
        val backIconSize = if (screenHeight < 600.dp) 60 else 75

        val fontSize = when {
            screenWidth < 360.dp -> 12
            screenWidth in 360.dp..400.dp -> 15
            else -> 17
        }
        val imageSize = when {
            screenHeight <= 600.dp -> 75.dp
            screenHeight in 600.dp..855.dp -> 90.dp
            else -> 95.dp
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Spacer(modifier = Modifier.height(smallPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )
            Spacer(modifier = Modifier.height(smallPadding))

            NavHeader(
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize,
                fontSize = fontSize
            ) {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(smallPadding))

            FinalSelectedCropBox(
                width = screenWidth,
                height=screenHeight,
                imageSize=imageSize,
                crop = cropId,
                isEnglish = isEnglish
            ) {
                viewModel.uploadSelectedCrop(selectedCrop)
            }
            Spacer(modifier = Modifier.height(largePadding))


            LotusRow(
                highlightedLotusPos = 3,
            )
            Spacer(modifier = Modifier.height(largePadding))

        }
    }
}

@Preview
@Composable
private fun FinalCropScreenPreview() {
    FinalSelectedCrop(rememberNavController(), cropId = 0)
}