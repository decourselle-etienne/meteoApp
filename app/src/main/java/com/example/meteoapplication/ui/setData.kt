package com.example.meteoapplication.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.format.DateFormat
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.meteoapplication.databinding.ActivityMainBinding
import com.example.meteoapplication.databinding.DailyPrevisionsBinding
import com.example.meteoapplication.models.Weather
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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

    public fun setSevenDaysData(resources: Resources, packageName: String,binding: DailyPrevisionsBinding, content: Weather?) {
        setSevenDayWeather(resources, packageName,binding,content)
        setSevenDayMaxTemp(binding,content)
        setSevenDayMinTemp(binding,content)
        setSevenDaySunrise(binding,content)
        setSevenDaySunset(binding,content)
        setSevenDayDates(binding, content)
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




    private fun setSevenDayWeather(resources: Resources, packageName: String,binding: DailyPrevisionsBinding,content: Weather?) {

        val dayOne = "w_"+ content?.daily?.weathercode?.get(0)
        val dayTwo = "w_"+ content?.daily?.weathercode?.get(1)
        val dayThree = "w_"+ content?.daily?.weathercode?.get(2)
        val dayFour = "w_"+ content?.daily?.weathercode?.get(3)
        val dayFive = "w_"+ content?.daily?.weathercode?.get(4)
        val daySix = "w_"+ content?.daily?.weathercode?.get(5)
        val daySeven = "w_"+ content?.daily?.weathercode?.get(6)

        val resIDOne = resources.getIdentifier(dayOne, "drawable", packageName)
        binding.dayOneContent.dayMeteo.setImageResource(resIDOne)
        val resIDTwo = resources.getIdentifier(dayTwo, "drawable", packageName)
        binding.dayTwoContent.dayMeteo.setImageResource(resIDTwo)
        val resIDThree = resources.getIdentifier(dayThree, "drawable", packageName)
        binding.dayThreeContent.dayMeteo.setImageResource(resIDThree)
        val resIDFour = resources.getIdentifier(dayFour, "drawable", packageName)
        binding.dayFourContent.dayMeteo.setImageResource(resIDFour)
        val resIDFive = resources.getIdentifier(dayFive, "drawable", packageName)
        binding.dayFiveContent.dayMeteo.setImageResource(resIDFive)
        val resIDSix = resources.getIdentifier(daySix, "drawable", packageName)
        binding.daySixContent.dayMeteo.setImageResource(resIDSix)
        val resIDSeven = resources.getIdentifier(daySeven, "drawable", packageName)
        binding.daySevenContent.dayMeteo.setImageResource(resIDSeven)
    }

    private fun setSevenDayMaxTemp(binding: DailyPrevisionsBinding,content: Weather?) {
        val values = content?.daily?.temperature_2m_max
        val dayOne = values?.get(0) ?: Int
        val dayTwo = values?.get(1) ?: Int
        val dayThree = values?.get(2) ?: Int
        val dayFour = values?.get(3) ?: Int
        val dayFive = values?.get(4) ?: Int
        val daySix = values?.get(5) ?: Int
        val daySeven = values?.get(6) ?: Int

        binding.dayOneContent.temperatureMaxValue.text = "$dayOne °C"
        binding.dayTwoContent.temperatureMaxValue.text = "$dayTwo °C"
        binding.dayThreeContent.temperatureMaxValue.text = "$dayThree °C"
        binding.dayFourContent.temperatureMaxValue.text = "$dayFour °C"
        binding.dayFiveContent.temperatureMaxValue.text = "$dayFive °C"
        binding.daySixContent.temperatureMaxValue.text = "$daySix °C"
        binding.daySevenContent.temperatureMaxValue.text = "$daySeven °C"
    }

    private fun setSevenDayMinTemp(binding: DailyPrevisionsBinding,content: Weather?) {
        val values = content?.daily?.temperature_2m_min
        val dayOne =values?.get(0) ?: Int
        val dayTwo = values?.get(1) ?: Int
        val dayThree = values?.get(2) ?: Int
        val dayFour = values?.get(3) ?: Int
        val dayFive = values?.get(4) ?: Int
        val daySix = values?.get(5) ?: Int
        val daySeven = values?.get(6) ?: Int

        binding.dayOneContent.temperatureMinValue.text = "$dayOne °C"
        binding.dayTwoContent.temperatureMinValue.text = "$dayTwo °C"
        binding.dayThreeContent.temperatureMinValue.text = "$dayThree °C"
        binding.dayFourContent.temperatureMinValue.text = "$dayFour °C"
        binding.dayFiveContent.temperatureMinValue.text = "$dayFive °C"
        binding.daySixContent.temperatureMinValue.text = "$daySix °C"
        binding.daySevenContent.temperatureMinValue.text = "$daySeven °C"
    }

    private fun setSevenDaySunrise(binding: DailyPrevisionsBinding,content: Weather?) {
        val values = content?.daily?.sunrise

        val sunriseSplitOne = values?.get(0)?.split("T")
        val sunriseOne = sunriseSplitOne?.get(1)
        val sunriseSplitTwo = values?.get(1)?.split("T")
        val sunriseTwo = sunriseSplitTwo?.get(1)
        val sunriseSplitThree = values?.get(2)?.split("T")
        val sunriseThree = sunriseSplitThree?.get(1)
        val sunriseSplitFour = values?.get(3)?.split("T")
        val sunriseFour = sunriseSplitFour?.get(1)
        val sunriseSplitFive = values?.get(4)?.split("T")
        val sunriseFive = sunriseSplitFive?.get(1)
        val sunriseSplitSix = values?.get(5)?.split("T")
        val sunriseSix = sunriseSplitSix?.get(1)
        val sunriseSplitSeven = values?.get(6)?.split("T")
        val sunriseSeven = sunriseSplitSeven?.get(1)


        binding.dayOneContent.sunrise.text = "$sunriseOne °C"
        binding.dayTwoContent.sunrise.text = "$sunriseTwo °C"
        binding.dayThreeContent.sunrise.text = "$sunriseThree °C"
        binding.dayFourContent.sunrise.text = "$sunriseFour °C"
        binding.dayFiveContent.sunrise.text = "$sunriseFive °C"
        binding.daySixContent.sunrise.text = "$sunriseSix °C"
        binding.daySevenContent.sunrise.text = "$sunriseSeven °C"
    }

    private fun setSevenDayDates(binding: DailyPrevisionsBinding,content: Weather?) {
        val values = content?.daily?.time

        val dateSplitOne = values?.get(0)?.split("T")
        val dateOne = LocalDate.parse(dateSplitOne?.get(0))
        val dateSplitTwo = values?.get(1)?.split("T")
        val dateTwo = LocalDate.parse(dateSplitTwo?.get(0))
        val dateSplitThree = values?.get(2)?.split("T")
        val dateThree = LocalDate.parse(dateSplitThree?.get(0))
        val dateSplitFour = values?.get(3)?.split("T")
        val dateFour = LocalDate.parse(dateSplitFour?.get(0))
        val dateSplitFive = values?.get(4)?.split("T")
        val dateFive = LocalDate.parse(dateSplitFive?.get(0))
        val dateSplitSix = values?.get(5)?.split("T")
        val dateSix = LocalDate.parse(dateSplitSix?.get(0))
        val dateSplitSeven = values?.get(6)?.split("T")
        val dateSeven = LocalDate.parse(dateSplitSeven?.get(0))

        val formater = DateTimeFormatter.ofPattern("EE\nd/M")


        binding.dayOneText.text = dateOne.format(formater)
        binding.dayTwoText.text = dateTwo.format(formater)
        binding.dayThreeText.text = dateThree.format(formater)
        binding.dayFourText.text = dateFour.format(formater)
        binding.dayFiveText.text = dateFive.format(formater)
        binding.daySixText.text = dateSix.format(formater)
        binding.daySevenText.text = dateSeven.format(formater)
    }

    private fun setSevenDaySunset(binding: DailyPrevisionsBinding,content: Weather?) {

        val values = content?.daily?.sunset
        val sunsetSplitOne = values?.get(0)?.split("T")
        val sunsetOne = sunsetSplitOne?.get(1)
        val sunsetSplitTwo = values?.get(1)?.split("T")
        val sunsetTwo = sunsetSplitTwo?.get(1)
        val sunsetSplitThree = values?.get(2)?.split("T")
        val sunsetThree = sunsetSplitThree?.get(1)
        val sunsetSplitFour = values?.get(3)?.split("T")
        val sunsetFour = sunsetSplitFour?.get(1)
        val sunsetSplitFive = values?.get(4)?.split("T")
        val sunsetFive = sunsetSplitFive?.get(1)
        val sunsetSplitSix = values?.get(5)?.split("T")
        val sunsetSix = sunsetSplitSix?.get(1)
        val sunsetSplitSeven = values?.get(6)?.split("T")
        val sunsetSeven = sunsetSplitSeven?.get(1)


        binding.dayOneContent.sunset.text = "$sunsetOne °C"
        binding.dayTwoContent.sunset.text = "$sunsetTwo °C"
        binding.dayThreeContent.sunset.text = "$sunsetThree °C"
        binding.dayFourContent.sunset.text = "$sunsetFour °C"
        binding.dayFiveContent.sunset.text = "$sunsetFive °C"
        binding.daySixContent.sunset.text = "$sunsetSix °C"
        binding.daySevenContent.sunset.text = "$sunsetSeven °C"
    }

}