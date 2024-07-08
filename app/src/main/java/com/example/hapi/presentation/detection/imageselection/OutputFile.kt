package com.example.hapi.presentation.detection.imageselection

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import java.io.File

fun getPhotoOutputFileOptions(
    context: Context
): ImageCapture.OutputFileOptions {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // For Android 10 (Q) and above
        val values = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "CameraX-${System.currentTimeMillis()}.jpg"
            )
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        ).build()

    } else {
        // For Android 9 (Pie) and below
        val photoDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        if (!photoDir.exists()) {
            photoDir.mkdirs()
        }

        val photoFile = File(
            photoDir,
            "CameraX-${System.currentTimeMillis()}.jpg"
        )

        ImageCapture.OutputFileOptions.Builder(photoFile).build()
    }
}