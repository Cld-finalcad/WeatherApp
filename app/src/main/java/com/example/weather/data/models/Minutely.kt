package com.example.weather.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Minutely (val dt: Int, val precipitation: Int)