package com.example.hapi

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.presentation.navigation.NavGraph
import com.example.hapi.ui.theme.HapiTheme
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.LocaleHelper
import com.example.hapi.util.SetStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var datastore: UserDataPreference

    @Inject
    lateinit var localeHelper: LocaleHelper

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (!hasRequiredPermissions()) {
                ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS,
                    0
                )
            }


            HapiTheme {
                SetStatusBarColor(color = YellowAppColor)
                val navController = rememberNavController()
                Box {
                    NavGraph(navController = navController)
                }
            }

        }
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        val PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_MEDIA_IMAGES
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun hasRequiredPermissions(): Boolean {
        return PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private suspend fun setLanguage() {
        val defaultLanguage = datastore.getLanguage() ?: Locale.getDefault().language
        datastore.setLanguage(defaultLanguage!!)
        val newContext = localeHelper.setLocale(defaultLanguage)
        Log.d("LOCALE", "new config: ${newContext == null}")
        applyOverrideConfiguration(newContext!!.resources.configuration)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        } else {
            super.applyOverrideConfiguration(overrideConfiguration)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onStart() {
        lifecycleScope.launch {
            setLanguage()
        }
        super.onStart()
    }
}
