package com.example.hapi

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.navigation.NavGraph
import com.example.hapi.ui.theme.HapiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (!hasRequiredPermissions()) {
                ActivityCompat.requestPermissions(
                    this,
                    CAMERA_PERMISSIONS,
                    0
                )
            }
            HapiTheme {
                val cameraController = remember {
                    LifecycleCameraController(applicationContext).apply {
                        setEnabledUseCases(
                            CameraController.IMAGE_CAPTURE or
                                    CameraController.VIDEO_CAPTURE
                        )
                    }
                }
                val navController= rememberNavController()
                Box{
                    NavGraph(navController = navController)
                }
            }
        }
    }

    fun takePhoto(
        cameraController: LifecycleCameraController
    ) {
//        cameraController.takePicture(
//            getPhotoOutputFileOptions(applicationContext),
//            ContextCompat.getMainExecutor(applicationContext),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                    Log.d("Save Photo", "done")
//                }
//
//                override fun onError(exception: ImageCaptureException) {
//                    Log.d("Save Photo", exception.toString())
//                }
//
//            }
//        )
    }
    companion object {
        val CAMERA_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA
        )
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERA_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}
