package com.example.hapi.presentation.detection.guest_cropselection

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.CropCollection
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.detection.imageselection.navigateToImageSelection
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun GuestCropSelection(
    navController: NavController,
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        val largePadding = screenHeight * .055f
        val horizontalPadding = screenWidth * .04f


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NavHeader(
                modifier = Modifier.padding(vertical = 16.dp),
                topText = stringResource(id = R.string.disease),
                downText = stringResource(id = R.string.detection),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = 80
            ) {
                navController.popBackStack()
            }


            Spacer(modifier = Modifier.height(largePadding))

            YellowBlackText(size = 16, text = stringResource(id = R.string.select_crop))

            Spacer(modifier = Modifier.height(largePadding))

            CropCollection(
                width = screenWidth
            ) { crop ->
                navController.navigateToImageSelection(crop = crop.name)
            }
        }
    }
}

@Preview
@Composable
private fun CropSelectionPreview() {
    GuestCropSelection(navController = rememberNavController())
}