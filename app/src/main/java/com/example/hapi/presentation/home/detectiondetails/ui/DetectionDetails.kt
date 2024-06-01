package com.example.hapi.presentation.home.detectiondetails.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.detection.guest_cropselection.navigateToGuestCropSelection
import com.example.hapi.presentation.home.common.DetectionInfo
import com.example.hapi.presentation.home.common.DetectionLowConfidenceCard
import com.example.hapi.presentation.home.common.DetectionResult
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.presentation.home.detectiondetails.viewmodel.DetectionDetailsViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.BASE_URL
import com.example.hapi.util.Dimens
import com.example.hapi.util.YellowBlackText
import com.example.hapi.util.toBitmap

@Composable
fun DetectionDetails(
    navController: NavController,
    detectionDetailsViewModel: DetectionDetailsViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    id: Int,
    isCurrentDetection: Boolean
) {
    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value
    LaunchedEffect(true, isEnglish) {
        if (!isCurrentDetection) {
            detectionDetailsViewModel.getRemoteDetectionDetailsById(id)
        } else {
            detectionDetailsViewModel.getCachedDiseaseDetectionResult(id)
        }
    }

    val image = detectionDetailsViewModel.byteArrayImage.collectAsState().value
    val username = detectionDetailsViewModel.username.collectAsState().value
    val date = detectionDetailsViewModel.date.collectAsState().value
    val time = detectionDetailsViewModel.time.collectAsState().value
    val crop = detectionDetailsViewModel.crop.collectAsState().value
    val confidence = detectionDetailsViewModel.confidence.collectAsState().value
    val diseaseList = detectionDetailsViewModel.diseaseList.collectAsState().value
    val imageUrl = detectionDetailsViewModel.imageUrl.collectAsState().value

    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        val (header, imageAndData, text, boxes) = createRefs()
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
            if (isCurrentDetection)
                navController.navigateToGuestCropSelection()
            else
                navController.popBackStack()
        }
        if (crop.isNotBlank()) {
            Row(
                modifier = Modifier
                    .constrainAs(imageAndData) {
                        top.linkTo(header.bottom, margin = 37.dp)
                        bottom.linkTo(text.top)
                    }
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(width = 3.dp, color = YellowAppColor)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .weight(1.2f)

                ) {
                    if (imageUrl.isNotBlank()) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp),
                            model = BASE_URL + imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp),
                            bitmap = image.toBitmap().asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }

                DetectionInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .weight(1f),
                    username = username,
                    date = date,
                    time = time,
                    color = YellowAppColor,
                    fontSize = 15
                )
            }

            Row(
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(imageAndData.bottom, margin = 22.dp)
                        bottom.linkTo(boxes.top, margin = 22.dp)
                    }
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(31.dp),
                    painter = painterResource(
                        id = getCropIcon(
                            crop
                        )
                    ),
                    contentDescription = "crop icon"
                )
                YellowBlackText(size = 24, text = crop.uppercase())
            }
            if (diseaseList.isEmpty()) {
                DetectionResult(
                    name = stringResource(id = R.string.healthy),
                    confidence = (confidence * 100).toInt().toString(),
                    modifier = Modifier
                        .constrainAs(boxes) {

                            top.linkTo(text.bottom, margin = Dimens.content_margin)
                            bottom.linkTo(bottomGuideLine)

                        }
                        .padding(horizontal = 12.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    alignment = Alignment.CenterHorizontally
                )

            } else {
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(boxes) {

                            top.linkTo(text.bottom, margin = Dimens.content_margin)
                            bottom.linkTo(bottomGuideLine)

                        },
                ) {
                    items(diseaseList.size) { index ->
                        val disease = diseaseList[index]
                        DetectionLowConfidenceCard(
                            modifier = Modifier.padding(vertical = 8.dp),
                            confidence = (disease.confidence * 100).toInt().toString(),
                            name = disease.name,
                        ) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(disease.infoLink))
                            startActivity(context, intent, null)
                        }
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