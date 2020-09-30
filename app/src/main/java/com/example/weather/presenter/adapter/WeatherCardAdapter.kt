package com.example.weather.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.weather.R
import com.example.weather.domain.models.WeatherModel

import kotlinx.android.synthetic.main.weather_cards.view.*

class WeatherCardAdapter : RecyclerView.Adapter<WeatherCardAdapter.MyViewHolder>() {

    var dataset: List<WeatherModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {

        fun showElements(dataset: List<WeatherModel>, position: Int) {

            this.itemView.context

            when (dataset[position].main) {
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
            cardView.city.text = dataset[position].timezone
            cardView.date.text = dataset[position].date
            cardView.main.text = dataset[position].main
            cardView.icon.load(
                this.itemView.context.getString(
                    R.string.imgsURL,
                    dataset[position].iconURL
                )
            )
//            this.itemView.context.resources.getQuantityString(R.plurals.notif, )
            cardView.temperature.text = (this.itemView.context.getString(
                R.string.temp,
                dataset[position].temperature
            ))
            cardView.pressure.text = (this.itemView.context.getString(
                R.string.pressure,
                dataset[position].pressure
            ))
            cardView.humidity.text = (this.itemView.context.getString(
                R.string.humidity,
                dataset[position].humidity
            ))
            cardView.wind.text = (this.itemView.context.getString(
                R.string.wind,
                dataset[position].wind
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
            cardView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.showElements(dataset!!, position)
        //holder.bind(dataset!!.get(position), itemTouchListener)
    }

    override fun getItemCount() = dataset?.size ?: 0
}
