package com.example.hapi.presentation.detection.imageselection

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.home.common.ORTextRow
import com.example.hapi.presentation.home.detectiondetails.navigateToDetectionDetails
import com.example.hapi.presentation.home.loading.Loading
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Crop
import com.example.hapi.util.toCompressedByteArray
import com.example.hapi.util.uriToByteArray
import kotlinx.coroutines.launch

@Composable
fun ImageSelection(
    navController: NavController,
    crop: Crop,
    viewModel: ImageSelectionViewModel = hiltViewModel()
) {

    val isLoading = viewModel.loading.collectAsState().value
    val errorMsg = viewModel.errorMsg.collectAsState().value
    val detectionId = viewModel.detectionId.collectAsState().value

    val context = LocalContext.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                androidx.camera.view.CameraController.IMAGE_CAPTURE
            )
        }
    }
    val coroutineScope = rememberCoroutineScope()

    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    fun takePhotoAndDetectDisease() {
        cameraController.takePicture(
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    viewModel.detectDisease(
                        crop.name,
                        image.toBitmap().toCompressedByteArray()!!
                    )
                    image.close()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.d("Save Photo", exception.toString())
                }

            }
        )
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { selectedImageUri ->
                coroutineScope.launch {
                    val byteArray = uriToByteArray(context.contentResolver, selectedImageUri)!!
                    Log.d("GetPhoto", "onCaptureSuccess: ${byteArray.size}")
                    viewModel.detectDisease(
                        crop.name,
                        byteArray
                    )
                }
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
            navController.popBackStack()
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
            takePhotoAndDetectDisease()
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
        if (isLoading) {
            Loading()
        }
        if (errorMsg != null) {
            //TODO: handle error msg
        }
        if (detectionId != null) {
            navController.navigateToDetectionDetails(
                id = detectionId.toInt().toString(),
                isCurrentDetection = "true"
            )
        }
    }

}


@Preview
@Composable
fun ImageCapturePreview() {
    ImageSelection(
        navController = rememberNavController(),
        crop = Crop.POTATO
    )
}