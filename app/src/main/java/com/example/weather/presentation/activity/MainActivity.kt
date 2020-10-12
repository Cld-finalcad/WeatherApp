package com.example.weather.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.domain.models.WeatherModel
import com.example.weather.presentation.viewmodel.Event
import com.example.weather.presentation.viewmodel.WeatherViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<WeatherViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        viewModel.weatherSelected.observe(
            this,
            Observer<Event<WeatherModel>> { weatherModelEvent ->
                val weatherModelConsumed = weatherModelEvent.isConsumed
                if (!weatherModelConsumed) {
                    weatherModelEvent.consume()
                    if (binding.detailsFragment == null) {
                        navHostFragment.navController.navigate(R.id.action_weatherFragment_to_detailsFragment)
                    }
                }

            })
    }
}