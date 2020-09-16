package com.example.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Temp (val day: Double, val min: Double, val max: Double, val night: Double, val eve: Double, val morn: Double)