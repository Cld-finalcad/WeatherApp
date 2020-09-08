package com.example.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class WeatherRepository (webservice: Webservice) {

    companion object  {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val service = retrofit.create(Webservice::class.java)

        val instance: WeatherRepository by lazy { WeatherRepository(service)}
    }

    fun getWeather() : LiveData<List<String>> {
        Log.d("Repository", "getWeather")
        getWeatherTest()
        val datasetc = listOf("test1", "test2", "test3", "test4","test1", "test2", "test3", "test4","test1", "test2", "test3", "test4"
            ,"test1", "test2", "test3", "test4","test1", "test2", "test3", "test4","test1", "test2", "test3", "test4")
        val dataset = MutableLiveData<List<String>>(datasetc)
        return dataset
    }

    fun getWeatherTest() : LiveData<String> {

        /*val dataset: LiveData<String> = liveData {
            val data = service.getWeather()
            emit(data)
        }*/
        val datasetr = MutableLiveData<String>()
        val dataset = service.getWeather()

        dataset.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                datasetr.value = response.body()
                Log.d("Repository", response.body()!!)
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("Repository", "Error : $t")
            }
        })

        return datasetr
    }
}