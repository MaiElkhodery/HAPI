package com.example.hapi.presentation.home.detectiondetails.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.detection.guest_cropselection.navigateToGuestCropSelection
import com.example.hapi.presentation.home.detectiondetails.viewmodel.DetectionDetailsViewModel
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.main.navigateToMainScreen
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.Tab

@Composable
fun DetectionDetails(
    navController: NavController,
    detectionDetailsViewModel: DetectionDetailsViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
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

    val role = detectionDetailsViewModel.role.collectAsState().value
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {

        val (header, data, result) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.2f)

        NavHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                },
            topText = stringResource(id = R.string.detection),
            downText = stringResource(id = R.string.result),
            imageId = if (isEnglish) R.drawable.back_btn else R.drawable.sign_back_btn_ar
        ) {
            if (isCurrentDetection) {
                mainViewModel.setSelectedTab(Tab.CAMERA)
                navController.navigateToMainScreen(role)
            } else {
                if (role.isNotBlank())
                    navController.popBackStack()
                else
                    navController.navigateToGuestCropSelection()
            }
        }
        if (crop.isNotBlank()) {

            DetectionDetailsData(
                modifier = Modifier
                    .constrainAs(data) {
                        top.linkTo(header.bottom, margin = 26.dp)
                        bottom.linkTo(result.top, margin = 16.dp)
                    },
                crop = crop,
                imageUrl = imageUrl,
                imageLocalUri = imageLocalUri,
                username = username,
                date = date,
                time = time
            )


            if (diseaseName.isBlank()) {
                HealthyResult(certainty = certainty,
                    modifier = Modifier.constrainAs(result) {
                        top.linkTo(data.bottom, margin = 26.dp)
                        bottom.linkTo(bottomGuideLine)
                    }
                )

            } else {
                DiseasedResult(
                    diseaseName = diseaseName, certainty = certainty,
                    modifier = Modifier.constrainAs(result) {
                        top.linkTo(data.bottom, margin = 26.dp)
                        bottom.linkTo(bottomGuideLine)
                    }
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

@Preview
@Composable
fun DetectionDetailsPreview() {
    DetectionDetails(
        rememberNavController(),
        id = 1,
        isCurrentDetection = false
    )
}