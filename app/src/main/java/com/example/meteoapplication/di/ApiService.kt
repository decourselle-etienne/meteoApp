package com.example.meteoapplication.di

import com.example.meteoapplication.models.DailyUrl
import com.example.meteoapplication.models.HourlyUrl
import com.example.meteoapplication.models.Weather
import com.example.meteoapplication.models.formated
import retrofit2.Response
import retrofit2.http.*

interface ApiService {



    @GET("/v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude : Double,
        @Query("longitude") longitude : Double,
        @Query("hourly") hourly: String = HourlyUrl().formated(),
        @Query("daily") daily: String = DailyUrl().formated(),
        @Query("current_weather") current_weather: String = "true",
        @Query("timezone") timezone: String ="Europe/Berlin"

        ) : Response<Weather>

   // @GET("birth-weather")
   // suspend fun getWeatherByBirth(@Query("latitude") latitude : Double,@Query("longitude") longitude : Double, @Query("") ): Response<Weather>

}