package com.example.meteoapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.meteoapplication.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {

    private var votrePosition : String = "Vos coordonées GPS sont : "
    var currentPositions : String? = null

    // Expose screen UI state
    //val currentWeather = MutableStateFlow(Weather(55.50, 10.10))

    // Handle business logic
    fun local(latitude:String, longitude:String): String {
        currentPositions = "latitude : " + latitude + " longitude : " + longitude
        var currentPosition = votrePosition + currentPositions
        return currentPosition
    }
}