package com.flobiztest.assignmentapp.util

import java.sql.DriverManager
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    /*
    *DATE_FORMAT = "dd MMMM yyyy"
    *The output will be -: 01 December 2020
    */
    fun convertToCalendar(inputDate: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.ENGLISH)
        val date = dateFormat.parse(inputDate)
        val d1 = SimpleDateFormat("dd MMMM yyyy",Locale.ENGLISH)
        val outD = d1.format(date!!)
        return outD
    }
}