package com.example.weather.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.weather.R
import com.example.weather.domain.models.WeatherModel

import kotlinx.android.synthetic.main.weather_cards.view.*

class WeatherCardAdapter(val weatherClickListener: WeatherClickListener) : RecyclerView.Adapter<WeatherCardAdapter.MyViewHolder>() {

    var dataset: List<WeatherModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(val cardView: CardView, val weatherClickListener: WeatherClickListener) : RecyclerView.ViewHolder(cardView) {

        fun showElements(weatherModel: WeatherModel) {

            cardView.setOnClickListener {
                weatherClickListener.onWeatherClick(weatherModel)
            }

            this.itemView.context

            when (weatherModel.main) {
                "Clear" -> cardView.cardlayout.setBackgroundResource(R.drawable.sunny)
                 "Clouds" -> cardView.cardlayout.setBackgroundResource(R.drawable.cloud)
                "Snow" -> cardView.cardlayout.setBackgroundResource(R.drawable.snow)
                "Rain" -> cardView.cardlayout.setBackgroundResource(R.drawable.rain)
                else -> {
                    cardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            cardView.context,
                            R.color.clear
                        )
                    )
                }
            }
            cardView.city.text = weatherModel.timezone
            cardView.date.text = weatherModel.date
            cardView.main.text = weatherModel.main
            cardView.icon.load(
                this.itemView.context.getString(
                    R.string.imgsURL,
                    weatherModel.iconURL
                )
            )
//            this.itemView.context.resources.getQuantityString(R.plurals.notif, )
            cardView.temperature.text = (this.itemView.context.getString(
                R.string.temp,
                weatherModel.temperature
            ))
            cardView.pressure.text = (this.itemView.context.getString(
                R.string.pressure,
                weatherModel.pressure
            ))
            cardView.humidity.text = (this.itemView.context.getString(
                R.string.humidity,
                weatherModel.humidity
            ))
            cardView.wind.text = (this.itemView.context.getString(
                R.string.wind,
                weatherModel.wind
            ))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_cards, parent, false) as CardView


        return MyViewHolder(
            cardView,
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