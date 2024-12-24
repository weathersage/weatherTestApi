package com.example.weathertest.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertest.data.Weather
import com.example.weathertest.repo.SharedPref
import com.example.weathertest.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val prefs: SharedPref
) : ViewModel() {
    private val repository = WeatherRepo()

    companion object {
        const val TAG = "WeatherScreenViewModel"
    }

    var searchedWeather by mutableStateOf<Weather?>(null)
    var selectedWeather by mutableStateOf<Weather?>(null)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var selectedCity by mutableStateOf<String?>(null)
    var searchedCity by mutableStateOf<String?>(null)

    init {
        selectedCity = fetchSavedCity()
        selectedCity?.let {
            fetchWeather(it, isSelectedWeather = true)
        }
    }

    fun updateSearchedCity(city: String) {
        searchedCity = city
    }

    fun updateSelectedCity(city: String) {
        saveCity(city)
        selectedCity = city
    }

    fun updateSelectedWeather(weather: Weather) {
        selectedWeather = weather
    }

    fun fetchWeather(city: String, isSelectedWeather: Boolean = false) {
        isLoading = true
        viewModelScope.launch {
            val response = repository.getWeather(city)
            if (response.isSuccessful) {
                val weather = response.body()
                if (weather != null) {
                    if (isSelectedWeather) {
                        selectedWeather = weather
                        updateSelectedCity(weather.location.name)
                        Log.d(TAG, "onSuccess selected weather: $searchedWeather")
                    } else {
                        searchedWeather = weather
                        updateSearchedCity(weather.location.name)
                        Log.d(TAG, "onSuccess searched weather: $searchedWeather")
                    }
                } else {
                    // TODO edge case of successful request but no weather available
                    error = "No available city for: $city"
                }
            } else {
                error = response.errorBody()?.string() ?:"There was an error, please try again."
                Log.d(TAG, "onFailure error: $error")
            }
            isLoading = false
        }
    }

    fun fetchSavedCity(): String? {
        val city = prefs.getCity()
        Log.d(TAG, "fetched saved city: $city")
        return city
    }

    fun saveCity(city: String) {
        Log.d(TAG, "saving city: $city")
        prefs.setCity(city)
    }
}