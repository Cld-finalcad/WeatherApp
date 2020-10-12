package com.example.weather.presentation.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.databinding.FragmentDetailsBinding
import com.example.weather.domain.models.Flag
import com.example.weather.domain.models.WeatherModel
import com.example.weather.presentation.viewmodel.Event
import com.example.weather.presentation.viewmodel.WeatherViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailsFragment @Inject constructor() : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by activityViewModels<WeatherViewModel> { viewModelFactory }

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherSelected.observe(
            viewLifecycleOwner,
            Observer<Event<WeatherModel>> { weatherModelEvent ->
                val weatherModel = weatherModelEvent.rawValue
                if (!weatherModel.flags.contains(Flag.SUNNY)) {
                    binding.sunglass.visibility = View.GONE
                    binding.sunglasstxt.visibility = View.GONE
                } else {
                    binding.sunglass.visibility = View.VISIBLE
                    binding.sunglasstxt.visibility = View.VISIBLE
                }
                if (!weatherModel.flags.contains(Flag.CHILLY)) {
                    binding.sweater.visibility = View.GONE
                    binding.sweatertxt.visibility = View.GONE
                } else {
                    binding.sweater.visibility = View.VISIBLE
                    binding.sweatertxt.visibility = View.VISIBLE
                }
                if (!weatherModel.flags.contains(Flag.WINDY)) {
                    binding.windbreaker.visibility = View.GONE
                    binding.windbreakertxt.visibility = View.GONE
                } else {
                    binding.windbreaker.visibility = View.VISIBLE
                    binding.windbreakertxt.visibility = View.VISIBLE
                }
                if (!weatherModel.flags.contains(Flag.COLD)) {
                    binding.winterjacket.visibility = View.GONE
                    binding.winterjackettxt.visibility = View.GONE
                } else {
                    binding.winterjacket.visibility = View.VISIBLE
                    binding.winterjackettxt.visibility = View.VISIBLE
                }
                if (weatherModel.flags.size == 0) {
                    binding.none.visibility = View.VISIBLE
                    binding.nonetxt.visibility = View.VISIBLE
                } else {
                    binding.none.visibility = View.GONE
                    binding.nonetxt.visibility = View.GONE
                }
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.attributes?.alpha = 0.8f
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}