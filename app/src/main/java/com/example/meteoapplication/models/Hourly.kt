package com.example.meteoapplication.models

data class Hourly(
    var time : List<String>,
    var temperature_2m : List<String>,
    var apparent_temperature : List<String>,
    var precipitation_probability : List<String>,
    var weathercode : List<String>,
    var cloudcover : List<String>
)
