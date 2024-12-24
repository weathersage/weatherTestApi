package com.example.weathertest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,

    @SerialName("tz_id")
    val tzID: String,

    @SerialName("localtime_epoch")
    val localtimeEpoch: Long,

    val localtime: String
)
