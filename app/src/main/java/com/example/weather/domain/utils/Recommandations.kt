package com.example.weather.domain.utils

import com.example.weather.domain.models.Flag

class Recommandations {
    companion object {
        fun setFlags(main: String, temperature: Double, wind: Double): Array<Flag> {
            val flags: Array<Flag> = arrayOf()

            if (main.equals("Clear")) flags.plus(Flag.SUNNY)
            if (temperature <= 278.15) flags.plus(Flag.COLD)
            if (temperature <= 293.15) flags.plus(Flag.CHILLY)
            if ((wind > 20) and (temperature <= 298.15)) flags.plus(Flag.WINDY)
            return flags
        }
    }

}