package com.example.weather.presentation.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.domain.models.WeatherModel
import com.example.weather.presentation.viewmodel.Event
import com.example.weather.presentation.viewmodel.WeatherViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_splash.*
import java.util.*
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController

            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars())
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE)
            }
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        if (savedInstanceState == null) {
            Handler(Looper.getMainLooper()).postDelayed({
                navHostFragment.navController.navigate(R.id.action_splashFragment_to_weatherFragment)
            }, 2000)
        }

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