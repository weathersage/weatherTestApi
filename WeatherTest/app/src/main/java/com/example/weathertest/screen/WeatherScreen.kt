package com.example.weathertest.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weathertest.data.Weather
import com.example.weathertest.viewmodel.WeatherScreenViewModel

/**
 * Some notes:
 *  - Unsure the exact font type used, so just used default Android
 *  - TODO - add in F to C setting option - Currently only support Fahrenheit
 *
 */

@Composable
fun WeatherScreenBase(
    viewModel: WeatherScreenViewModel = hiltViewModel()
) {
    WeatherScreen(
        viewModel.selectedWeather,
        viewModel.searchedWeather,
        viewModel.selectedCity,
        viewModel.searchedCity,
        viewModel.isLoading,
        viewModel.error,
        onFetchWeather = { searchedCity ->
            viewModel.fetchWeather(searchedCity)
        },
        onWeatherSelected = { weather ->
            viewModel.updateSelectedCity(weather.location.name)
            viewModel.updateSelectedWeather(weather)
        }
    )
}

@Composable
fun WeatherScreen(
    selectedWeather: Weather?,
    searchedWeather: Weather?,
    selectedCity: String?,
    searchedCity: String?,
    isLoading: Boolean = false,
    error: String? = null,
    onFetchWeather: (String) -> Unit,
    onWeatherSelected: (Weather) -> Unit
) {
    Box(
        Modifier.background(color = Color.White).fillMaxSize()) {

        Column {
            WeatherSearchBar(
                searchedWeather,
                searchedCity,
                isLoading,
                error,
                onFetchWeather,
                onWeatherSelected = {
                    onWeatherSelected(it)
                }
            )

            SelectedWeatherContent(selectedWeather, isLoading)
        }
    }
}
