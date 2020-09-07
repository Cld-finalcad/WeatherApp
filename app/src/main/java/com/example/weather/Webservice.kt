package com.example.weather

import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {
    @GET("")
    fun getUser(): String
}