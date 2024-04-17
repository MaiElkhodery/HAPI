package com.example.hapi.presentation.home.detectiondetails

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.home.common.DetectionInfo
import com.example.hapi.presentation.home.common.DetectionLowConfidence
import com.example.hapi.presentation.home.common.DetectionResult
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.BASE_URL
import com.example.hapi.util.Crop
import com.example.hapi.util.Dimens
import com.example.hapi.util.text.YellowBlackText
import com.example.hapi.util.toBitmap

@Composable
fun DetectionDetails(
    navController: NavController,
    viewModel: DetectionDetailsViewModel = hiltViewModel(),
    detectionId: Int,
    local: Boolean = false
) {
    val isLocal by remember {
        mutableStateOf(local)
    }
    LaunchedEffect(true) {
        Log.d("DetectionDetails", "detectionId: $detectionId, isLocal: $isLocal")
        if (isLocal) viewModel.getCachedDetection(detectionId)
        else viewModel.getDetection(detectionId)
    }
    val remoteDetection = viewModel.remoteDetectionItem.collectAsState().value
    val localDetection = viewModel.localDetection.collectAsState().value

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
            downText = stringResource(id = R.string.result)
        ) {
            navController.popBackStack()
        }

        if (remoteDetection != null || localDetection != null) {
            Log.d("DetectionDetails", "localDetection: $localDetection")
            Log.d("DetectionDetails", "remoteDetection: $remoteDetection")

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
                        .weight(1.5f)

                ) {
                    if (!isLocal) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp),
                            model = BASE_URL + remoteDetection!!.image_url,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp),
                            bitmap = localDetection!!.detection.image.toBitmap().asImageBitmap(),
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
                    username = if (!isLocal) remoteDetection!!.username else localDetection!!.detection.detectionMaker,
                    date = if (!isLocal) remoteDetection!!.date else localDetection!!.detection.date,
                    time = if (!isLocal) remoteDetection!!.time else localDetection!!.detection.time,
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
                val crop =
                    if (!isLocal) remoteDetection!!.crop.uppercase() else localDetection!!.detection.crop.uppercase()

                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(31.dp),
                    painter = painterResource(
                        id = getCropIcon(
                            Crop.valueOf(crop)
                        )
                    ),
                    contentDescription = "crop icon"
                )
                YellowBlackText(size = 24, text = crop)
            }
            val isHealthy =
                if (!isLocal) remoteDetection!!.detection.isHealthy else localDetection!!.detection.isHealthy
            Log.d("DetectionDetails", "isHealthy: $isHealthy")
            if (isHealthy) {
                DetectionResult(
                    name = stringResource(id = R.string.healthy),
                    confidence = if (!isLocal) remoteDetection!!.detection.confidence.toString()
                    else localDetection!!.detection.confidence.toString(),
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

                    if (isLocal) {
                        val diseases = localDetection!!.diseases
                        items(diseases.size) { index ->
                            val disease = diseases[index]
                            DetectionLowConfidence(
                                modifier = Modifier.padding(vertical = 8.dp),
                                confidence = disease.confidence.toString(),
                                name = disease.name,
                            ) {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(disease.infoLink))
                                startActivity(context, intent, null)
                            }

                        }
                    } else {

                        val diseases = remoteDetection!!.detection.diseases
                        items(diseases.size) { index ->
                            val disease = diseases[index]
                            DetectionLowConfidence(
                                modifier = Modifier.padding(vertical = 8.dp),
                                confidence = disease.confidence.toString(),
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
}

@Preview
@Composable
fun DetectionDetailsPreview() {
    DetectionDetails(
        rememberNavController(),
        detectionId = 0
    )
}