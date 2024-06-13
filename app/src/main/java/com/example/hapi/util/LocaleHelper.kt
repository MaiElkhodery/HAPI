package com.example.hapi.util

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class LocaleHelper @Inject constructor(@ApplicationContext private val context: Context) {
    fun setLocale(language: String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        Log.d("LOCALE",locale.language)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        Log.d("LOCALE",configuration.locales.toLanguageTags())

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))

        context.createConfigurationContext(configuration)
    }

}