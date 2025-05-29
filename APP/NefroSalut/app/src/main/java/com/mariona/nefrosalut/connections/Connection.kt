package com.mariona.nefrosalut.connections

import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import okhttp3.Request
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object Connection {
    private const val TOKEN = "8OoKQ1nQSmPcnOCfh0mso8bHbjHods6grbwHW0V3c1fa4b8b"

    private val authInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val newRequest: Request = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN")
            .build()
        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClientNefrosalut = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .connectTimeout(3600, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(this).build()

    }

    private val builderNefrosalut = Retrofit.Builder()
        .baseUrl("https://nefrosalutapi.onrender.com/public/")
        .client(okHttpClientNefrosalut)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nefroSalutService: Endpoint = builderNefrosalut.create()
}