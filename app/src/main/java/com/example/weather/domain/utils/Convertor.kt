package com.example.weather.domain.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Convertor {

    companion object {

        val contryCode = Locale.getDefault().country

        fun getDate(date: Int): String {

            Log.d("CONVERTOR", date.toString() + " " + date.toLong().toString())


            if ("FR".equals(contryCode)) return getDateTime(date.toString(), "dd/MM HH:mm z", "GMT+2")

            return getDateTime(date.toString(), "MM/dd HH:mm z", "GMT-4")
        }

        fun getTemp(temp: Double): Double {
            if ("FR".equals(contryCode))
                return temp - 273.15
            return temp
        }

        private fun getDateTime(s: String, format: String, timeZone: String): String {
            try {
                val sdf = SimpleDateFormat(format)
                sdf.timeZone = TimeZone.getTimeZone(timeZone)
                val netDate = Date(s.toLong() * 1000)
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }

    }
}