package com.example.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WeatherRepository {
    fun getWeather() : LiveData<List<String>> {
        val datasetc = listOf("test1", "test2", "test3", "test4","test1", "test2", "test3", "test4","test1", "test2", "test3", "test4"
            ,"test1", "test2", "test3", "test4","test1", "test2", "test3", "test4","test1", "test2", "test3", "test4")
        val dataset = MutableLiveData<List<String>>()
        dataset.value = datasetc
        return dataset
    }
}