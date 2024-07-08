package com.example.hapi.presentation.home.detectiondetails.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.detectiondetails.viewmodel.DetectionDetailsViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH

@Composable
fun DetectionDetails(
    navController: NavController,
    detectionDetailsViewModel: DetectionDetailsViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    id: Int,
    isCurrentDetection: Boolean
) {
    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    LaunchedEffect(true, isEnglish) {
        if (!isCurrentDetection) {
            detectionDetailsViewModel.getRemoteDetectionDetailsById(id)
        } else {
            detectionDetailsViewModel.getCachedDiseaseDetectionResult(id)
        }
    }

    val imageLocalUri = detectionDetailsViewModel.imageLocalUri.collectAsState().value
    val username = detectionDetailsViewModel.username.collectAsState().value
    val date = detectionDetailsViewModel.date.collectAsState().value
    val time = detectionDetailsViewModel.time.collectAsState().value
    val crop = detectionDetailsViewModel.crop.collectAsState().value
    val certainty = detectionDetailsViewModel.certainty.collectAsState().value
    val diseaseName = detectionDetailsViewModel.diseaseName.collectAsState().value
    val imageUrl = detectionDetailsViewModel.imageUrl.collectAsState().value
    val link = detectionDetailsViewModel.link.collectAsState().value

    val context = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val screenHeight = maxHeight
        val screenWidth = maxWidth

        val smallPadding = screenHeight * 0.03f
        val largePadding = screenHeight * 0.05f
        val backIconSize = if (screenHeight < 650.dp) 60 else 75
        val horizontalPadding = if (screenWidth < 400.dp) 24.dp else 28.dp



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(smallPadding))

            NavHeader(
                modifier = Modifier,
                topText = stringResource(id = R.string.detection),
                downText = stringResource(id = R.string.result),
                imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar,
                imageSize = backIconSize
            ) {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(largePadding))

            if (crop.isNotBlank()) {
                DetectionDetailsData(
                    modifier = Modifier,
                    crop = crop,
                    imageUrl = imageUrl,
                    imageLocalUri = imageLocalUri,
                    username = username,
                    date = date,
                    time = time
                )

                Spacer(modifier = Modifier.height(largePadding))

                if (diseaseName.isBlank()) {
                    HealthyResult(certainty = certainty)

                } else {

                    DiseasedResult(
                        diseaseName = diseaseName, certainty = certainty
                    ) {
                        ContextCompat.startActivity(
                            context,
                            Intent(Intent.ACTION_VIEW, Uri.parse(link)),
                            null
                        )
                    }

                }

            }
        }
    }
}

@Preview
@Composable
fun DetectionDetailsPreview() {
    DetectionDetails(
        rememberNavController(),
        id = 1,
        isCurrentDetection = false
    )
}