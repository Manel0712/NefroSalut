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
    private const val TOKEN = "eOAnQ3ZLex43Yinb8rWLGa1XjrTrW7JLXGfAB9PFf2640000"

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
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(this).build()

    }

    private val builderNefrosalut = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/")
        .client(okHttpClientNefrosalut)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val nefroSalutService: Endpoint = builderNefrosalut.create()
}