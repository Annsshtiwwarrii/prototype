package com.example.prototype

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://alkyetest-738240239910.us-central1.run.app"
                )
            .addConverterFactory(GsonConverterFactory.create())

            .build()
            .create(ApiService::class.java)
    }
}