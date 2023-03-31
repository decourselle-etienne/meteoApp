package com.example.meteoapplication.models

data class HourlyUnits(
    var time : String,
    var temperature_2m: String,
    var apparent_temperature: String,
    var precipitation_probability: String,
    var weathercode : String,
    var cloudcover : String,
    )
