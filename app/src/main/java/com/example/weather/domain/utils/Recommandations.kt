package com.example.weather.domain.utils

import android.util.Log
import com.example.weather.domain.models.Flag

class Recommandations {
    companion object {
        fun setFlags(main: String, temperature: Double, wind: Double): MutableList<Flag> {
            val flags: MutableList<Flag> = mutableListOf()


            if (main.equals("Clear")) flags.add(Flag.SUNNY)
            if (temperature <= KELVIN_0) flags.add(Flag.COLD)                     // TODO Extract Const
            if (temperature <= 293.15) flags.add(Flag.CHILLY)
            if ((wind > 20) and (temperature <= WINDY)) flags.add(Flag.WINDY)

            return flags
        }

        const val KELVIN_0 = 278.15
        const val WINDY = KELVIN_0 + 20
    }

}