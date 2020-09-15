package com.example.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.ui.adapter.WeatherCardAdapter
import com.example.weather.ui.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(requireContext())
        val viewModel: WeatherViewModel by viewModels()
        val viewAdapter = WeatherCardAdapter()

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

        }

        viewModel.weather.observe(viewLifecycleOwner) {
            viewAdapter.dataset = it
        }


    }

}