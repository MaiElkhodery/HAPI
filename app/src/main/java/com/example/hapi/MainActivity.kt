package com.example.hapi

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.presentation.navigation.NavGraph
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.HapiTheme
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.LocaleHelper
import com.example.hapi.util.SetStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var datastore: UserDataPreference

    @Inject
    lateinit var localeHelper: LocaleHelper

    @Inject
    lateinit var context: Context

    private var IS_RTL: Boolean = false

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

            runBlocking {
                initLanguage()
            }

            val languageViewModel: LanguageViewModel by lazy {
                LanguageViewModel(datastore)
            }
            val language = languageViewModel.appLanguage.collectAsState().value
            val isRtl = getRtlMode(language)
            val direction =
                if (isRtl) LocalLayoutDirection provides LayoutDirection.Rtl else LocalLayoutDirection provides LayoutDirection.Ltr


            CompositionLocalProvider(direction) {
                HapiTheme {
                    SetStatusBarColor(color = YellowAppColor)
                    val navController = rememberNavController()
                    Box {
                        NavGraph(navController = navController) { language ->
                            lifecycleScope.launch {
                                datastore.setLanguage(language)
                                setLanguage(language)
                                recreate()
                            }
                        }
                    }
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

    private suspend fun initLanguage() {
        val defaultLanguage = datastore.getLanguage() ?: Locale.getDefault().language
        datastore.setLanguage(defaultLanguage)
        setLanguage(defaultLanguage)
    }

    private fun getRtlMode(lang: String): Boolean {
        IS_RTL = lang == "ar"
        return lang == "ar"
    }

    private fun setLanguage(language: String) {
        val config = resources.configuration
        val locale = Locale(language)
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}

