package com.example.weathertest.repo

import com.example.weathertest.data.Weather
import com.example.weathertest.network.ApiClient
import retrofit2.Response

class WeatherRepo {
    private val weatherService = ApiClient.weatherService
    suspend fun getWeather(city: String): Response<Weather> {
        return weatherService.getWeather(city)
    }
}
