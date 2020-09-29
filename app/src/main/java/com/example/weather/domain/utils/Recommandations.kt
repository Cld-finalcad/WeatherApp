package com.example.weather.domain.utils

import android.util.Log
import com.example.weather.domain.models.Flag

class Recommandations {
    companion object {
        fun setFlags(main: String, temperature: Double, wind: Double): MutableList<Flag> {
            var flags: MutableList<Flag> = mutableListOf()


            if (main.equals("Clear")) flags.add(Flag.SUNNY)
            if (temperature <= 278.15) flags.add(Flag.COLD)
            if (temperature <= 293.15) flags.add(Flag.CHILLY)
            if ((wind > 20) and (temperature <= 298.15)) flags.add(Flag.WINDY)

            return flags
        }
    }

}