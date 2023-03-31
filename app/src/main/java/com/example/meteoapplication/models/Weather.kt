package com.example.meteoapplication.models

data class Weather(
    var latitude: Double,
    var longitude: Double,
    var generationTime : Float,
    var utc_offset_seconds : String,
    var timezone: String,
    var timezone_abbreviation : String,
    var elevation : Double,
    var current_weather: CurrentWeather,
    var hourly_units: HourlyUnits,
    var hourly: Hourly,
    var daily_units: DailyUnits,
    var daily: Days
)
