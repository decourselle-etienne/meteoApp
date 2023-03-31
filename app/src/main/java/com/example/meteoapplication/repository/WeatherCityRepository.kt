package com.example.meteoapplication.repository


import androidx.annotation.WorkerThread
import com.example.meteoapplication.dao.WeatherCityDao
import com.example.meteoapplication.entity.WeatherCityEntity
import kotlinx.coroutines.flow.Flow

class WeatherCityRepository(private val wordDao: WeatherCityDao) {
    val allCities: Flow<List<WeatherCityEntity>> = wordDao.getWeatherCities()

    @WorkerThread
    suspend fun insert(city: WeatherCityEntity) {
        wordDao.insert(city)
    }
}