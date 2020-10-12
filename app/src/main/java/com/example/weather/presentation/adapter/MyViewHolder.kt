package com.example.weather.presentation.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.weather.R
import com.example.weather.databinding.WeatherCardsBinding
import com.example.weather.domain.models.WeatherModel

class MyViewHolder(val binding: WeatherCardsBinding, val weatherClickListener: WeatherClickListener) : RecyclerView.ViewHolder(binding.cardView) {

    fun showElements(weatherModel: WeatherModel) {

        binding.cardView.setOnClickListener {
            weatherClickListener.onWeatherClick(weatherModel)
        }


        this.itemView.context

        when (weatherModel.main) {
            "Clear" -> binding.cardlayout.setBackgroundResource(R.drawable.sunny)
            "Clouds" -> binding.cardlayout.setBackgroundResource(R.drawable.cloud)
            "Snow" -> binding.cardlayout.setBackgroundResource(R.drawable.snow)
            "Rain" -> binding.cardlayout.setBackgroundResource(R.drawable.rain)
            else -> {
                binding.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.cardView.context,
                        R.color.clear
                    )
                )
            }
        }
        binding.city.text = weatherModel.timezone
         binding.date.text = weatherModel.date
         binding.main.text = weatherModel.main
        binding.icon.load(
            this.itemView.context.getString(
                R.string.imgsURL,
                weatherModel.iconURL
        ))

         //this.itemView.context.resources.getQuantityString(R.plurals.notif, )
         binding.temperature.text = (this.itemView.context.getString(
             R.string.temp,
             weatherModel.temperature
         ))
         binding.pressure.text = (this.itemView.context.getString(
             R.string.pressure,
             weatherModel.pressure
         ))
         binding.humidity.text = (this.itemView.context.getString(
             R.string.humidity,
             weatherModel.humidity
         ))
         binding.wind.text = (this.itemView.context.getString(
             R.string.wind,
             weatherModel.wind
         ))
    }
}
