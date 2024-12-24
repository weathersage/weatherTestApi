package com.example.weathertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathertest.screen.WeatherScreenBase
import com.example.weathertest.ui.theme.WeatherTestTheme
import com.example.weathertest.viewmodel.WeatherScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Note: Currently not supporting dark mode
            WeatherTestTheme(darkTheme = false) {
                val viewModel: WeatherScreenViewModel = hiltViewModel<WeatherScreenViewModel>()
                WeatherScreenBase(viewModel)
            }
        }
    }
}
