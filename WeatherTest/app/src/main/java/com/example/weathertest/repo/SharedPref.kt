package com.example.weathertest.repo

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val CITY_TAG = "CITY_TAG"

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context : Context){
    private val prefs: SharedPreferences =
        context.getSharedPreferences(CITY_TAG, Context.MODE_PRIVATE)

    fun getCity(): String? {
        return prefs.getString(CITY_TAG, null)
    }
    fun setCity(city: String) {
        prefs.edit().putString(CITY_TAG, city).apply()
    }
}
