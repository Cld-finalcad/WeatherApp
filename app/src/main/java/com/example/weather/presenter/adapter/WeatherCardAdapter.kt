package com.example.weather.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.weather.data.models.Weather
import com.example.weather.R
import com.example.weather.domain.models.WeatherModel
import kotlinx.android.synthetic.main.weather_cards.view.*

class WeatherCardAdapter: RecyclerView.Adapter<WeatherCardAdapter.MyViewHolder>() {

    var dataset: List<WeatherModel>?= null
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        fun showElements(dataset: List<WeatherModel>, position: Int) {

            cardView.setOnClickListener(View.OnClickListener {
                cardView.findNavController().navigate(R.id.action_weatherFragment_to_detailsFragment)
            })

            this.itemView.context

            when(dataset[position].main) {
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
            cardView.city.text = dataset[position].timezone
            cardView.date.text = dataset[position].date
            cardView.main.text = dataset[position].main
            cardView.icon.load(this.itemView.context.getString(
                R.string.imgsURL,
                dataset[position].iconURL
            ))
//            this.itemView.context.resources.getQuantityString(R.plurals.notif, )
            cardView.temperature.text = (this.itemView.context.getString(R.string.temp,
                dataset[position].temperature))
            cardView.pressure.text = (this.itemView.context.getString(R.string.pressure,
                dataset[position].pressure))
            cardView.humidity.text = (this.itemView.context.getString(R.string.humidity,
                dataset[position].humidity))
            cardView.wind.text = (this.itemView.context.getString(R.string.wind,
                dataset[position].wind))
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

    override fun getItemCount() = dataset?.size ?: 0
}
