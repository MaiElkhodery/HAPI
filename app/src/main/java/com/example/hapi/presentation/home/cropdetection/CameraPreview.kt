package com.example.hapi.presentation.home.cropdetection

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.example.hapi.R
import com.example.hapi.presentation.home.common.CameraIcon
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop
import com.example.hapi.util.text.YellowBoldText

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    crop: Crop,
    cameraController: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner,
    onCaptureClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CropDetectionText(crop = crop)
        CameraBox(
            cameraController = cameraController,
            lifecycleOwner = lifecycleOwner
        ){
            onCaptureClick()
        }
    }
}

@Composable
fun CropDetectionText(
    crop: Crop
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        YellowBoldText(text = stringResource(id = R.string.now_detecting), size = 16)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = getCropIcon(crop)),
                contentDescription = "crop icon",
                modifier = Modifier
                    .size(26.dp)
                    .padding(end = 6.dp)
            )
            YellowBoldText(text = crop.name, size = 16)
        }
    }
}

@Composable
fun CameraBox(
    cameraController: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner,
    onCaptureClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(top = 12.dp).fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 35.dp)
                .border(
                    width = 3.dp,
                    color = YellowAppColor,
                    shape = RoundedCornerShape(16.dp)
                ).fillMaxSize()
        ) {
            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        this.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                },
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp))
            )
        }
        CameraIcon(
            iconColor = DarkGreenAppColor,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            onCaptureClick()
        }
    }
}

@Preview
@Composable
private fun CameraBoxPreview() {

}