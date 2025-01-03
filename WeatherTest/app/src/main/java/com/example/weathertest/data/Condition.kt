package com.example.weathertest.data

import kotlinx.serialization.Serializable

@Serializable
data class Condition (
    val text: String,
    val icon: String,
    val code: Long
)
