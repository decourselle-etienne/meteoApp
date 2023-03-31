package com.example.meteoapplication.models

data class Days(
    var time : Array<String>,
    var weathercode: Array<Int>,
    var temperature_2m_max: Array<Double>,
    var temperature_2m_min: Array<Double>,
    var sunrise: Array<String>,
    var sunset: Array<String>,
    var uv_index_max: Array<Double>

)
