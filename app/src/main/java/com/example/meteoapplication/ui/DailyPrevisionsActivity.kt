package com.example.meteoapplication.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.example.meteoapplication.R
import com.example.meteoapplication.database.CityRoomDatabase
import com.example.meteoapplication.databinding.DailyPrevisionsBinding
import com.example.meteoapplication.di.ApiClient
import com.example.meteoapplication.entity.WeatherCityEntity
import com.example.meteoapplication.models.Weather
import com.example.meteoapplication.repository.WeatherCityRepository
import com.example.meteoapplication.viewModel.MainViewModel
import kotlinx.coroutines.*
import java.util.*

class DailyPrevisionsActivity : AppCompatActivity() {

    var setData = setData()
    lateinit var binding: DailyPrevisionsBinding
    var mainIntent = intent

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Database Room
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = CityRoomDatabase.getDatabase(this, applicationScope)
        val repository = WeatherCityRepository(database.cityDao())
        applicationScope.launch(Dispatchers.IO) { repository.insert(WeatherCityEntity("Test")) }


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()
        if (mainIntent == null) {
            var content = mainIntent
            //getSerializableExtra("content", Weather::class.java)

            println(content)
        }
        else {
            println("Erreur mainIntent = null")
        }



        binding = DailyPrevisionsBinding.inflate(layoutInflater)
        val view = binding.root

        val button = binding.myToolbar.menuButton
        button.setImageResource(R.drawable.back)
        button.setOnClickListener {
            val intent_ = Intent(this, MainActivity::class.java)
            startActivity(intent_)
        }
        //setData.setSevenDaysData(resources,packageName, binding, content)
        setContentView(view)
    }
}