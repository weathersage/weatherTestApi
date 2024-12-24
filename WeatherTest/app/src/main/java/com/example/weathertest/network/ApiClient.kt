package com.example.weathertest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val URL = "https://api.weatherapi.com/"
    private const val API_VERSION = "v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL + API_VERSION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}
