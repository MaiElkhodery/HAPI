package com.example.hapi.presentation.detection.guest_cropselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.auth.common.Title
import com.example.hapi.presentation.auth.signup.landownersignup.cropselection.SignupCropSelectionContent
import com.example.hapi.presentation.detection.imageselection.navigateToImageSelection
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun GuestCropSelection(
    navController: NavController,
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        val (header, text, crops) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.3f)

        NavHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                },
            topText = stringResource(id = R.string.disease),
            downText = stringResource(id = R.string.detection),
            imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar
        ) {
            navController.popBackStack()
        }


        Title(
            title = stringResource(id = R.string.select_crop),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(header.bottom, margin = 45.dp)
                bottom.linkTo(crops.top, margin = 5.dp)
            }
        )

        SignupCropSelectionContent(
            modifier = Modifier.constrainAs(crops) {
                top.linkTo(text.bottom, margin = 5.dp)
                bottom.linkTo(bottomGuideLine)
            }
        ) { crop ->
            navController.navigateToImageSelection(crop = crop.name)
        }
    }
}

@Preview
@Composable
private fun CropSelectionPreview() {
    GuestCropSelection(navController = rememberNavController())
}