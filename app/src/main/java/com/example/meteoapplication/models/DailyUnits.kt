package com.example.meteoapplication.models

data class DailyUnits(
    var time : String,
    var weathercode : String,
    var temperature_2m_max:String,
    var temperature_2m_min : String,
    var sunrise : String,
    var sunset : String,
    var uv_index_max : String
)
