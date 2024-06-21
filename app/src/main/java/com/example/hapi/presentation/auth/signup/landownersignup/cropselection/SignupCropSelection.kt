package com.example.hapi.presentation.auth.signup.landownersignup.cropselection

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
import com.example.hapi.presentation.common.CropCollection
import com.example.hapi.presentation.home.common.getCropName
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun SignupCropSelection(
    navController: NavController,
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value== ENGLISH
    
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxWidth

        val smallPadding = screenHeight * 0.034f
        val largePadding = screenHeight * 0.044f
        val logoSize = if (screenHeight < 600.dp) 55.dp else 75.dp
        val backIconSize = if (screenHeight < 600.dp) 60 else 75
        val horizontalPadding = if (screenWidth < 400.dp) 24.dp else 28.dp

        val fontSize = when {
            screenWidth <= 360.dp -> 14
            screenWidth in 360.dp..400.dp -> 16
            else -> 18
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.size(smallPadding))

            Logo(
                modifier = Modifier.fillMaxWidth()
                    .size(logoSize)
            )
            Spacer(modifier = Modifier.size(smallPadding))

            NavHeader(
                topText = stringResource(id = R.string.setting_up),
                downText = stringResource(id = R.string.your_account),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize,
                fontSize = fontSize
            ) {
                navController.navigateToCropSelectionStrategy()
            }
            Spacer(modifier = Modifier.size(smallPadding))

            Title(title = stringResource(id = R.string.avilable_crop))
            Spacer(modifier = Modifier.size(smallPadding))

            CropCollection(
                width=screenWidth,
            ) { crop ->
                navController.navigateToFinalSelectedCrop(getCropName(crop = crop.name))
            }
            Spacer(modifier = Modifier.size(largePadding))

            LotusRow(
                highlightedLotusPos = 2,
            )
            Spacer(modifier = Modifier.size(largePadding))
            
        }
    }
}

@Preview
@Composable
private fun CropChoosePreview() {
    SignupCropSelection(rememberNavController())
}