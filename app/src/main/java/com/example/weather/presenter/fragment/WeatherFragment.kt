package com.example.weather.presenter.fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.domain.models.WeatherModel
import com.example.weather.presenter.adapter.WeatherCardAdapter
import com.example.weather.presenter.adapter.WeatherClickListener
import com.example.weather.presenter.viewmodel.WeatherViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

class WeatherFragment @Inject constructor() : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by activityViewModels<WeatherViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(requireContext())

        val viewAdapter = WeatherCardAdapter(object : WeatherClickListener {
            override fun onWeatherClick(weatherModel: WeatherModel) {
                viewModel.sendWeather(weatherModel)
            }
        })

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }

        viewModel.weather.observe(viewLifecycleOwner) {
            viewAdapter.dataset = it
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

}