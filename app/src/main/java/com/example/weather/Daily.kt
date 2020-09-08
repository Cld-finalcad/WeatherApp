package com.example.weather

data class Daily (val dt:Int, val sunrise: Int, val sunset: Int, val temp: Temp, val feels_like: FeelsLike, val pressure: Int, val humidity: Int,
                  val dew_point: Float, val wind_speed: Float, val wind_deg: Int, val weather: List<WeatherItem>, val clouds: Int, val pop: Int, val uvi: Float) {
}

