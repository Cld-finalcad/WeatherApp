package com.example.weather.presenter.fragment

import RecyclerItemClickListener
import android.content.Context
import android.content.Context.MODE_PRIVATE
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
import com.example.weather.presenter.adapter.WeatherCardAdapter
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

        val viewAdapter = WeatherCardAdapter()

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }

        recycler.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recycler,
                object : RecyclerItemClickListener.ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        Log.d("WeatherFragment", "onClick")
                        viewModel.sendWeather(viewModel.weather.value!!.get(position))
                        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                            findNavController().navigate(R.id.action_weatherFragment_to_detailsFragment)
                    }

                    override fun onLongClick(view: View?, position: Int) {}
                })
        )

        viewModel.weather.observe(viewLifecycleOwner) {
            viewAdapter.dataset = it
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

}