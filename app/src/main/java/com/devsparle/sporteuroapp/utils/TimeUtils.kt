package com.devsparle.sporteuroapp.utils

import org.ocpsoft.prettytime.PrettyTime
import java.util.*

object TimeUtils {

    fun fromEpochTimeToAgo(epoch: Long): String {
        val prettyTime = PrettyTime(Locale.getDefault())
        val ago: String = prettyTime.format(Date(epoch))
        return ago
    }
}