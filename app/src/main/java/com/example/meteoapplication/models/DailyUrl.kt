package com.example.meteoapplication.models

data class DailyUrl(
    val weathercode: String = "weathercode",
    val temperature_2m_max: String = "temperature_2m_max",
    val temperature_2m_min: String = "temperature_2m_min" ,
    val sunrise : String = "sunrise",
    val sunset : String = "sunset",
    val uv_index_max : String = "uv_index_max"
    )

fun DailyUrl.formated() : String {
    return "$weathercode,$temperature_2m_max,$temperature_2m_min,$sunrise,$sunset,$uv_index_max"
}
