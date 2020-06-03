package com.ilustris.moovi.model.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    fun convertDate(dia: String): String? {
        var date = dia
        val df: DateFormat = SimpleDateFormat("yyy-MM-dd", Locale.US)
        try {
            val result = df.parse(dia)
            val dtformater = SimpleDateFormat("yyy", Locale.US)
            result?.let {
                date = dtformater.format(result)

            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println(date)
        return date
    }


}