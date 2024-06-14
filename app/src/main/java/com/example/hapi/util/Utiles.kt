package com.example.hapi.util

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun createMultiPartBody(
    imageByteArray: ByteArray
): MultipartBody.Part {
    val compressedBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
    val outputStream = ByteArrayOutputStream()
    compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
    val requestBody = outputStream.toByteArray().toRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("image", getTime(), requestBody)
}

fun getTime(): String {
    return SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
}

fun getCurrentDateAsString(): String {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return "$year/$month/$day"
}

fun getCurrentTimeAsString(): String {
    val calendar = Calendar.getInstance()
    return SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.time)
}

suspend fun uriToByteArray(contentResolver: ContentResolver, uri: Uri): ByteArray? {
    return withContext(Dispatchers.IO) {
        var inputStream: InputStream? = null
        var outputStream: ByteArrayOutputStream? = null
        try {
            inputStream = contentResolver.openInputStream(uri)
            if (inputStream != null) {
                outputStream = ByteArrayOutputStream()
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                outputStream.toByteArray()
            } else {
                null
            }
        } finally {
            inputStream?.close()
            outputStream?.close()
        }
    }
}


fun ByteArray.toBitmap(): Bitmap {
    return BitmapFactory.decodeByteArray(this, 0, size)
}


suspend fun isNetworkConnected(): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL("https://www.google.com")
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = 500
            urlConnection.connect()
            urlConnection.disconnect()
            true
        } catch (e: Exception) {
            false
        }
    }
}

fun crossFadeAnimation(
    state: Int
): TweenSpec<Float> {
    return when (state) {
        0 -> tween(
            durationMillis = 400,
            easing = LinearOutSlowInEasing
        )

        1 -> tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )

        2 -> tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )

        3 -> tween(
            durationMillis = 400,
            easing = LinearOutSlowInEasing
        )

        else -> tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        )
    }
}
