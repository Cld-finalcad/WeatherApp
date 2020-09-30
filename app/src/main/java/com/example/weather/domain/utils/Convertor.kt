package com.example.weather.domain.utils

import android.util.Log
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class Convertor {

    companion object {

        val contryCode = Locale.getDefault().country

        fun getDate(date: Date): String {

            if ("FR".equals(contryCode)) return getDateTime(date, "GMT+2")

            return getDateTime(date, "GMT-4")
        }

        fun getTemp(temp: Double): Double {
            if ("FR".equals(contryCode))
                return temp - 273.15
            return temp
        }

        private fun getDateTime(date: Date, timeZone: String): String {
            try {
                val format = " dd HH:mm z"

                val sdf = SimpleDateFormat(format)
                sdf.timeZone = TimeZone.getTimeZone(timeZone)


                val symbols = DateFormatSymbols()

                val cal = Calendar.getInstance()
                cal.time = date

                return symbols.shortWeekdays[cal.get(Calendar.DAY_OF_WEEK)] + ", " + symbols.shortMonths[cal.get(Calendar.MONTH)] + sdf.format(date)
            } catch (e: Exception) {
                return e.toString()
            }
        }

    }
}