package com.example.meteoapplication.models

data class HourlyUrl(
    val temperature_2m: String = "temperature_2m",
    val apparent_temperature: String = "apparent_temperature",
    val precipitation_probability: String = "precipitation_probability" ,
    val weathercode : String = "weathercode",
    val cloudcover : String = "cloudcover",
)

public fun HourlyUrl.formated(): String {
    return "$temperature_2m,$apparent_temperature,$precipitation_probability,$weathercode,$cloudcover"
}