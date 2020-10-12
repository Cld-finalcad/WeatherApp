package com.example.weather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.WeatherCardsBinding
import com.example.weather.domain.models.WeatherModel

class WeatherCardAdapter(val weatherClickListener: WeatherClickListener) : RecyclerView.Adapter<MyViewHolder>() {

    var dataset: List<WeatherModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_cards, parent, false) as CardView

        val binding = WeatherCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(
            binding,
            weatherClickListener
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.showElements(dataset[position])
    }

    override fun getItemCount() = dataset.size
}

interface WeatherClickListener {
    fun onWeatherClick(weatherModel: WeatherModel)
}