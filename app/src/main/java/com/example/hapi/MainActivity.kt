package com.example.hapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import com.example.hapi.presentation.navigation.NavGraph
import com.example.hapi.ui.theme.HapiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HapiTheme {
                val navController= rememberNavController()
                Box{
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
