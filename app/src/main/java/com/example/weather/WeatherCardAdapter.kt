package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_cards.view.*

class WeatherCardAdapter: RecyclerView.Adapter<WeatherCardAdapter.MyViewHolder>() {

    var dataset = listOf<String>("")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        fun showElements(dataset: List<String>, position: Int) {
            cardView.textView.text = dataset[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WeatherCardAdapter.MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_cards, parent, false) as CardView

        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.showElements(dataset, position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}