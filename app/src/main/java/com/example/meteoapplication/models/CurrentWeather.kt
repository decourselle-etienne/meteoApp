package com.example.meteoapplication.models

data class CurrentWeather(
    var temperature: String,
    var windspeed: Double,
    var winddirection: Double,
    var weathercode: Int,
    var time: String)