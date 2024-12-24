package com.example.weathertest.network

import com.example.weathertest.BuildConfig
import com.example.weathertest.data.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current.json")
    suspend fun getWeather(@Query("q") city: String, @Query("key") key: String = BuildConfig.API_KEY): Response<Weather>
}