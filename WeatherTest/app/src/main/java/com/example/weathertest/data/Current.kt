package com.example.weathertest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current (
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Long,

    @SerialName("last_updated")
    val lastUpdated: String,

    @SerialName("temp_c")
    val tempC: Double,

    @SerialName("temp_f")
    val tempF: Double,

    @SerialName("is_day")
    val isDay: Long,

    val condition: Condition,

    @SerialName("wind_mph")
    val windMph: Double,

    @SerialName("wind_kph")
    val windKph: Double,

    @SerialName("wind_degree")
    val windDegree: Long,

    @SerialName("wind_dir")
    val windDir: String,

    @SerialName("pressure_mb")
    val pressureMB: Long,

    @SerialName("pressure_in")
    val pressureIn: Double,

    @SerialName("precip_mm")
    val precipMm: Long,

    @SerialName("precip_in")
    val precipIn: Long,

    val humidity: Long,
    val cloud: Long,

    @SerialName("feelslike_c")
    val feelslikeC: Double,

    @SerialName("feelslike_f")
    val feelslikeF: Double,

    @SerialName("windchill_c")
    val windchillC: Double,

    @SerialName("windchill_f")
    val windchillF: Double,

    @SerialName("heatindex_c")
    val heatindexC: Double,

    @SerialName("heatindex_f")
    val heatindexF: Double,

    @SerialName("dewpoint_c")
    val dewpointC: Double,

    @SerialName("dewpoint_f")
    val dewpointF: Double,

    @SerialName("vis_km")
    val visKM: Long,

    @SerialName("vis_miles")
    val visMiles: Long,

    val uv: Double,

    @SerialName("gust_mph")
    val gustMph: Double,

    @SerialName("gust_kph")
    val gustKph: Double
)
