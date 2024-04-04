package com.example.hapi

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
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
                val navController = rememberNavController()
                Box {
                    NavGraph(navController = navController)
                }
            }
        }
    }

    companion object {
        val CAMERA_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA
        )
    }

    fun hasRequiredPermissions(): Boolean {
        return CAMERA_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}
