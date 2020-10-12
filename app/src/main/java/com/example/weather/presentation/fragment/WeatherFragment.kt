package com.example.weather.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.domain.models.WeatherModel
import com.example.weather.presentation.adapter.WeatherCardAdapter
import com.example.weather.presentation.adapter.WeatherClickListener
import com.example.weather.presentation.viewmodel.WeatherViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WeatherFragment @Inject constructor() : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by activityViewModels<WeatherViewModel> { viewModelFactory }

    private var _binding: FragmentWeatherBinding?= null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(requireContext())

        val viewAdapter = WeatherCardAdapter(object : WeatherClickListener {
            override fun onWeatherClick(weatherModel: WeatherModel) {
                viewModel.sendWeather(weatherModel)
            }
        })

        binding.recycler.apply {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}