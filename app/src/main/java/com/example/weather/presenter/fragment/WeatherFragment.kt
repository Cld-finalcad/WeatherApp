package com.example.weather.presenter.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.*
import com.example.weather.presenter.adapter.WeatherCardAdapter
import com.example.weather.presenter.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

class WeatherFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by viewModels<WeatherViewModel> {viewModelFactory}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("WEATHERFRAGMENT", "On Created")

        val viewManager = LinearLayoutManager(requireContext())

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

    override fun onPause() {
        super.onPause()
        Log.d("WEATHERFRAGMENT", "On Pause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("WEATHERFRAGMENT", "On Resume")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as WeatherApplication).applicationComponent.inject(this)
    }

}