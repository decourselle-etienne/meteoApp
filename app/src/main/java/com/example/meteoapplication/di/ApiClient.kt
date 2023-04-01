package com.example.meteoapplication.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient{

    private const val BASE_URL: String = "https://api.open-meteo.com"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }



    private fun httpClient(context : Context) : OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(myCache)
            .addNetworkInterceptor(CacheInterceptor())
            .build()
    }

    private fun retrofit (context : Context) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient(context))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun apiService(context: Context) :  ApiService {
        return retrofit(context).create(ApiService::class.java)
    }
}

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}