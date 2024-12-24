package com.example.weathertest.data

import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    val location: Location,
    val current: Current
)
