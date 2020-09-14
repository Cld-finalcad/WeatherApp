package com.example.weather.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.weather.business.Weather
import com.example.weather.R
import kotlinx.android.synthetic.main.weather_cards.view.*

class WeatherCardAdapter: RecyclerView.Adapter<WeatherCardAdapter.MyViewHolder>() {

    var dataset: Weather?= null
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        fun showElements(dataset: Weather, position: Int) {

            when(dataset.daily[position].weather[0].main) {
                "Clear" -> cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,
                    R.color.clear
                ))
                "Clouds" -> cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,
                    R.color.clouds
                ))
                "Snow" -> cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,
                    R.color.snow
                ))
                "Rain" -> cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,
                    R.color.rain
                ))
                else -> {
                    cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,
                        R.color.clear
                    ))
                }
            }
            cardView.city.text = dataset.timezone
            cardView.date.text = dataset.daily[position].dt.toString()
            cardView.main.text = dataset.daily[position].weather[0].main
            cardView.icon.load("http://openweathermap.org/img/wn/"
                    + dataset.daily[position].weather[0].icon + "@2x.png")
            cardView.temperature.text = (dataset.daily[position].temp.day.toString() + " ° K")
            cardView.pressure.text = ("Pressure: " + dataset.daily[position].pressure.toString())
            cardView.humidity.text = ("Humidity: " + dataset.daily[position].humidity.toString())
            cardView.wind.text = ("Wind: " + dataset.daily[position].wind_speed.toString() + " m/s")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_cards, parent, false) as CardView

        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.showElements(dataset!!, position)
    }

    override fun getItemCount() = dataset?.daily?.size ?: 0
}