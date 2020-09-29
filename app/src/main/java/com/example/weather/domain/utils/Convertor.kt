package com.example.weather.domain.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Convertor {

    companion object {

        val contryCode = Locale.getDefault().country

        fun getDate(date: Date): String {

            if ("FR".equals(contryCode)) return getDateTime(date, "dd/MM HH:mm z", "GMT+2")

            return getDateTime(date, "MM/dd HH:mm z", "GMT-4")
        }

        fun getTemp(temp: Double): Double {
            if ("FR".equals(contryCode))
                return temp - 273.15
            return temp
        }

        private fun getDateTime(date: Date, format: String, timeZone: String): String {
            try {
                val sdf = SimpleDateFormat(format)
                sdf.timeZone = TimeZone.getTimeZone(timeZone)
                //val netDate = Date(s.toLong() * 1000)
                return sdf.format(date)
            } catch (e: Exception) {
                return e.toString()
            }
        }

    }
}