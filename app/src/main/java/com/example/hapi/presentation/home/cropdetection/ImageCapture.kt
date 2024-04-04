package com.example.hapi.presentation.home.cropdetection

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.home.common.ORTextRow
import com.example.hapi.presentation.home.cropselection.navigateToCropSelection
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Crop
import java.io.ByteArrayOutputStream

@Composable
fun ImageCapture(
    navController: NavController,
    crop: Crop,
) {

    val context = LocalContext.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                androidx.camera.view.CameraController.IMAGE_CAPTURE
            )
        }
    }
    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    fun takePhoto() {
        cameraController.takePicture(
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val buffer = image.planes[0].buffer
                    val imageData = ByteArray(buffer.remaining())
                    buffer.get(imageData)

                    //TODO: SEND IT TO BACKEND

                    Log.d("ImageCaptured", "${image.format} , ${image.height} , ${image.width}")
                    image.close()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.d("Take Photo", exception.toString())
                }

            }
        )
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { selectedImageUri ->
                Log.d("Selected Image Uri", selectedImageUri.toString())
                val inputStream =
                    context.contentResolver.openInputStream(selectedImageUri)
                val byteArrayOutputStream = ByteArrayOutputStream()

                inputStream?.use { input ->
                    byteArrayOutputStream.use { output ->
                        input.copyTo(output)
                    }
                }

                val byteArray = byteArrayOutputStream.toByteArray()
            }
        }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {


        val (header, camera, bottomText) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.07f)

        NavHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                },
            topText = stringResource(id = R.string.disease),
            downText = stringResource(id = R.string.detection)
        ) {
            navController.navigateToCropSelection()
        }

        CameraPreview(
            crop = crop,
            modifier = Modifier
                .constrainAs(camera) {
                    top.linkTo(header.bottom, margin = 33.dp)
                    bottom.linkTo(bottomText.top, margin = 33.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            cameraController = cameraController,
            lifecycleOwner = LocalLifecycleOwner.current
        ) {
            takePhoto()
        }

        ORTextRow(
            modifier = Modifier
                .constrainAs(bottomText) {
                    bottom.linkTo(bottomGuideLine)
                },
            text = stringResource(id = R.string.choose_from_photos),
            icon = Icons.Default.PhotoLibrary
        ) {
            galleryLauncher.launch("image/*")
        }
    }
}


@Preview
@Composable
fun ImageCapturePreview() {
    ImageCapture(
        navController = rememberNavController(),
        crop = Crop.POTATO
    )
}