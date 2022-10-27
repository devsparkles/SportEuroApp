package com.devsparle.sporteuroapp.utils

import org.ocpsoft.prettytime.PrettyTime
import java.sql.Timestamp

object ConvertUtils {

    fun getPrettyTime(date: Double): String {
        try {
            val stamp = Timestamp(date.toLong() * 1000)
            val t = PrettyTime()
            return t.format(stamp)
        } catch (e: Exception) {
            LogApp.e(e.message)
            return ""
        }
    }

}