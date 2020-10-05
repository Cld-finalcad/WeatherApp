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
import com.example.weather.R
import com.example.weather.domain.models.Flag
import com.example.weather.domain.models.WeatherModel
import com.example.weather.presentation.viewmodel.Event
import com.example.weather.presentation.viewmodel.WeatherViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.recommandations_view.view.sunglass
import kotlinx.android.synthetic.main.recommandations_view.view.sunglasstxt
import kotlinx.android.synthetic.main.recommandations_view.view.sweater
import kotlinx.android.synthetic.main.recommandations_view.view.sweatertxt
import kotlinx.android.synthetic.main.recommandations_view.view.windbreaker
import kotlinx.android.synthetic.main.recommandations_view.view.windbreakertxt
import kotlinx.android.synthetic.main.recommandations_view.view.winterjacket
import kotlinx.android.synthetic.main.recommandations_view.view.winterjackettxt
import javax.inject.Inject

class DetailsFragment @Inject constructor() : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by activityViewModels<WeatherViewModel> { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*if (requireActivity().supportFragmentManager.findFragmentById(R.id.weather_fragment) != null)
            scroll_recommandation.setBackgroundResource(0)*/

        viewModel.weatherSelected.observe(
            viewLifecycleOwner,
            Observer<Event<WeatherModel>> { weatherModelEvent ->
                val weatherModel = weatherModelEvent.rawValue
                if (!weatherModel.flags.contains(Flag.SUNNY)) {
                    view.sunglass.visibility = View.GONE
                    view.sunglasstxt.visibility = View.GONE
                } else {
                    view.sunglass.visibility = View.VISIBLE
                    view.sunglasstxt.visibility = View.VISIBLE
                }
                if (!weatherModel.flags.contains(Flag.CHILLY)) {
                    view.sweater.visibility = View.GONE
                    view.sweatertxt.visibility = View.GONE
                } else {
                    view.sweater.visibility = View.VISIBLE
                    view.sweatertxt.visibility = View.VISIBLE
                }
                if (!weatherModel.flags.contains(Flag.WINDY)) {
                    view.windbreaker.visibility = View.GONE
                    view.windbreakertxt.visibility = View.GONE
                } else {
                    view.windbreaker.visibility = View.VISIBLE
                    view.windbreakertxt.visibility = View.VISIBLE
                }
                if (!weatherModel.flags.contains(Flag.COLD)) {
                    view.winterjacket.visibility = View.GONE
                    view.winterjackettxt.visibility = View.GONE
                } else {
                    view.winterjacket.visibility = View.VISIBLE
                    view.winterjackettxt.visibility = View.VISIBLE
                }
                if (weatherModel.flags.size == 0) {
                    view.none.visibility = View.VISIBLE
                    view.nonetxt.visibility = View.VISIBLE
                } else {
                    view.none.visibility = View.GONE
                    view.nonetxt.visibility = View.GONE
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

}