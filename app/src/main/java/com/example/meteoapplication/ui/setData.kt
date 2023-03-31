package com.example.meteoapplication.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import com.example.meteoapplication.databinding.ActivityMainBinding
import com.example.meteoapplication.models.Weather

class setData {

    public fun setAllData(resources: Resources, packageName: String,binding: ActivityMainBinding, content: Weather?) {
        setTemperatureReelle(binding,content)
        setTemperatureRessentie(binding,content)
        setCurrentMeteo(resources, packageName,binding,content)
        setPrecipitationsPrevision(binding,content)
        setCouvertureTotale(binding,content)
        setUvIndex(binding,content)
        setDayData(resources, packageName,binding,content)
    }

    private fun setDayData(resources: Resources, packageName: String,binding: ActivityMainBinding, content: Weather?) {
        setDayWeather(resources, packageName,binding,content)
        setDayMaxTemp(binding,content)
        setDayMinTemp(binding,content)
        setDaySunrise(binding,content)
        setDaySunset(binding,content)
    }
    private fun checkCurrentTime(content: Weather?): Int {
        val currentTime = content?.current_weather?.time as String
        val times = content.hourly.time
        return times.indexOf(currentTime)
    }

    @SuppressLint("SetTextI18n")
    private fun setTemperatureRessentie(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentTime(content)
        val value = content?.hourly?.apparent_temperature?.get(index) as String
        binding.temperatureRessentie.text = "ressentie $value °C"


    }

    @SuppressLint("SetTextI18n")
    private fun setTemperatureReelle(binding: ActivityMainBinding,content: Weather?) {
        val value = content?.current_weather?.temperature as String
        binding.temperatureReelle.text = "$value °C"
    }

    @SuppressLint("DiscouragedApi")
    private fun setCurrentMeteo(resources: Resources, packageName: String, binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentTime(content)
        val value = content?.hourly?.weathercode?.get(index) as String
        val filename = "w_$value"
        val resID = resources.getIdentifier(filename, "drawable", packageName)
        binding.currentMeteo.setImageResource(resID)

    }

    @SuppressLint("SetTextI18n")
    private fun setPrecipitationsPrevision(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentTime(content)
        val value = content?.hourly?.precipitation_probability?.get(index) as String
        binding.progressBarLayout.progress.progress = value.toInt()
        binding.progressBarLayout.valeur.text = "$value%"
    }

    @SuppressLint("SetTextI18n")
    private fun setCouvertureTotale(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentTime(content)
        val value = content?.hourly?.cloudcover?.get(index) as String
        binding.cloudcoverLayout.cloudcoverProgress.progress = value.toInt()
        binding.cloudcoverLayout.cloudcoverValeur.text = "$value%"
    }

    private fun setUvIndex(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentDay(content)
        val value = content?.daily?.uv_index_max?.get(index)
        binding.uvIndexLayout.uvValue.text = "$value"
    }

    private fun setDayWeather(resources: Resources, packageName: String,binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentDay(content)
        val value = content?.daily?.weathercode?.get(index)
        val filename = "w_$value"
        val resID = resources.getIdentifier(filename, "drawable", packageName)
        binding.dailyLayout.dayMeteo.setImageResource(resID)
    }

    private fun setDayMaxTemp(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentDay(content)
        val value = content?.daily?.temperature_2m_max?.get(index)
        binding.dailyLayout.temperatureMaxValue.text = "$value °C"
    }

    private fun setDayMinTemp(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentDay(content)
        val value = content?.daily?.temperature_2m_min?.get(index)
        binding.dailyLayout.temperatureMinValue.text = "$value °C"
    }

    private fun setDaySunrise(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentDay(content)
        val value = content?.daily?.sunrise?.get(index) as String
        val sunriseSplit = value.split("T")
        val sunrise = sunriseSplit[1]
        binding.dailyLayout.sunrise.text = "$sunrise"
    }

    private fun setDaySunset(binding: ActivityMainBinding,content: Weather?) {
        val index = checkCurrentDay(content)
        val value = content?.daily?.sunset?.get(index) as String
        val sunsetSplit = value.split("T")
        val sunset = sunsetSplit[1]
        binding.dailyLayout.sunset.text = "$sunset"
    }



    private fun checkCurrentDay(content: Weather?): Int {
        val currentTime = content?.current_weather?.time as String
        val times = content.daily.time
        val day = currentTime.split("T")
        return times.indexOf(day[0])
    }



}