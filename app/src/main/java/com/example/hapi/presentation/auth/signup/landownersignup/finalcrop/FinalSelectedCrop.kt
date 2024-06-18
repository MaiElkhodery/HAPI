package com.example.hapi.presentation.auth.signup.landownersignup.finalcrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.hapi.presentation.progress.navToProgress
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun FinalSelectedCrop(
    navController: NavController,
    crop: String,
    viewModel: AuthViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val error = viewModel.errorMsg.collectAsState().value
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    val cropIsUploaded = viewModel.cropIsUploaded.collectAsState().value

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

        val smallPadding = screenHeight * 0.018f
        val largePadding = screenHeight * 0.028f
        val logoSize = if (screenHeight < 600.dp) 60.dp else 80.dp
        val backIconSize = if (screenHeight < 600.dp) 60 else 80
        val horizontalPadding = if (screenWidth < 400.dp) 24.dp else 28.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.size(smallPadding))

            Logo(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(logoSize)
            )
            Spacer(modifier = Modifier.size(smallPadding))

            NavHeader(
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize
            ) {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.size(smallPadding))

            FinalSelectedCropContent(
                crop = crop
            ) {
                viewModel.uploadSelectedCrop(crop.lowercase())
            }
            Spacer(modifier = Modifier.size(largePadding))


            LotusRow(
                highlightedLotusPos = 3,
            )
            Spacer(modifier = Modifier.size(largePadding))

        }
    }
}

@Preview
@Composable
private fun FinalCropScreenPreview() {
    FinalSelectedCrop(rememberNavController(), crop = "WHEAT")
}