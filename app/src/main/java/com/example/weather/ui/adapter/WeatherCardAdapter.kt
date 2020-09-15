package com.example.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.weather.business.model.Weather
import com.example.weather.R
import com.example.weather.business.model.Daily
import com.example.weather.business.model.WeatherItem
import kotlinx.android.synthetic.main.weather_cards.view.*

class WeatherCardAdapter: RecyclerView.Adapter<WeatherCardAdapter.MyViewHolder>() {

    var dataset: Weather?= null
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        fun showElements(dataset: Weather, position: Int) {

            val weather = dataset.daily[position].weather[0]
            val daily = dataset.daily[position]

            this.itemView.context

            when(weather.main) {
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
            cardView.date.text = daily.dt.toString()
            cardView.main.text = weather.main
            cardView.icon.load(String.format(this.itemView.context.getString(R.string.imgsURL),
                weather.icon))
            cardView.temperature.text = (String.format(this.itemView.context.getString(R.string.temp),
                daily.temp.day))
            cardView.pressure.text = (String.format(this.itemView.context.getString(R.string.pressure),
               daily.pressure))
            cardView.humidity.text = (String.format(this.itemView.context.getString(R.string.humidity),
               daily.humidity))
            cardView.wind.text = (String.format(this.itemView.context.getString(R.string.wind),
                daily.wind_speed))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_cards, parent, false) as CardView


        return MyViewHolder(
            cardView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.showElements(dataset!!, position)
    }

    override fun getItemCount() = dataset?.daily?.size ?: 0
}