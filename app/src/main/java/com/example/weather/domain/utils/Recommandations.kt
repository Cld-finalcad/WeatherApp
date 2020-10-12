package com.example.weather.domain.utils

import com.example.weather.domain.models.Flag

class Recommandations {
    companion object {
        fun setFlags(main: String, temperature: Double, wind: Double): MutableList<Flag> {
            val flags: MutableList<Flag> = mutableListOf()


            if (main.equals("Clear")) flags.add(Flag.SUNNY)
            if (temperature <= KELVIN_0) flags.add(Flag.COLD)
            if (temperature <= CHILLY) flags.add(Flag.CHILLY)
            if ((wind > WINDY_SPEED) and (temperature <= WINDY_TEMP)) flags.add(Flag.WINDY)

            return flags
        }

        const val KELVIN_0 = 278.15
        const val WINDY_TEMP = KELVIN_0 + 20
        const val WINDY_SPEED = 20
        const val CHILLY = KELVIN_0 + 15
    }

}