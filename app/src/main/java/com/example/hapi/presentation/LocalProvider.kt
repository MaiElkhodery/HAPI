package com.example.hapi.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import java.util.Locale

//@Composable
//fun LocaleProvider(
//    viewModel: LanguageViewModel = hiltViewModel(),
//    content: @Composable () -> Unit
//) {
//    val context = LocalContext.current
//
//    val language = viewModel.appLanguage.collectAsState(Locale.getDefault().language).value
//
//    val updatedContext = remember(language) {
//        viewModel.localeHelper.setLocale(language)
//    }
//
//    CompositionLocalProvider(LocalContext provides updatedContext!!) {
//        content()
//    }
//}
