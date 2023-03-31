package com.example.meteoapplication.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.*
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.example.meteoapplication.R
import com.example.meteoapplication.database.CityRoomDatabase
import com.example.meteoapplication.databinding.ActivityMainBinding
import com.example.meteoapplication.di.ApiClient
import com.example.meteoapplication.entity.WeatherCityEntity
import com.example.meteoapplication.models.Weather
import com.example.meteoapplication.repository.WeatherCityRepository
import com.example.meteoapplication.viewModel.MainViewModel
import kotlinx.coroutines.*
import java.util.*


class MainActivity  : AppCompatActivity() {

    var setData = setData()
    var content: Weather? = null
    lateinit var binding: ActivityMainBinding
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    lateinit var city : String

    private var androidLocationManager: LocationManager? = null
    private var androidLocationListener: LocationListener? = null
    private val REQUEST_CODE_UPDATE_LOCATION = 42


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getCity(latitude: Double, longitude:Double) {
        Locale.setDefault(Locale.FRENCH)
        val gcd = Geocoder(this, Locale.FRANCE)
        var allAddresses: MutableList<Address>? = gcd.getFromLocation(latitude, longitude, 1)
        //getFromLocation(latitude, longitude, 1, GeocodeListener { addresses: MutableList<Address> ->  })

        if (allAddresses!!.size > 0) {
            println(allAddresses!![0].locality)
            city = allAddresses!![0].locality
        } else {
        }

    }
    private fun androidUpdateLocation(viewModel : ViewModel) {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_UPDATE_LOCATION
            )
        } else {
            androidLocationManager =
                this.getSystemService(LOCATION_SERVICE) as LocationManager
            androidLocationListener = object : LocationListener {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onLocationChanged(loc: Location) {
                    latitude = loc.latitude
                    longitude = loc.longitude
                    getCity(latitude, longitude)

                    println("Votre ville $city")
                    searchWeather(viewModel, latitude, longitude)
                    setCityText(city)
                }

                @Deprecated("Deprecated in Java")
                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                }

            }
            androidLocationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                50F,
                androidLocationListener!!
            )

        }
    }
    override fun onPause() {
        super.onPause()
        if (androidLocationListener != null) {
            if (androidLocationManager == null) {
                androidLocationManager =
                    this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            }
            androidLocationManager?.removeUpdates(androidLocationListener!!)
            androidLocationManager = null
            androidLocationListener = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Database Room
        val applicationScope = CoroutineScope(SupervisorJob())
        val database = CityRoomDatabase.getDatabase(this, applicationScope)
        val repository = WeatherCityRepository(database.cityDao())
        applicationScope.launch(Dispatchers.IO) { repository.insert(WeatherCityEntity("Test")) }


        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()
        androidUpdateLocation(viewModel)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    private fun searchWeather(viewModel: ViewModel, latitude: Double, longitude: Double) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiClient.apiService.getWeather(latitude, longitude)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful && response.body() != null) {
                        content = response.body()
                        setData.setAllData(resources,packageName, binding, content)

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error Occurred if: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } catch (e: Exception) {
                    println("ERROR = " + e.message)
                    Toast.makeText(
                        this@MainActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }

    private fun setCityText(city:String) {
        binding.villeChoisie.text = city
    }

}

