package com.example.prototype

import retrofit2.http.GET

interface ApiService {
    @GET("myapp/list/?format=api")
    suspend fun getSliderData(): List<dataClass>
}