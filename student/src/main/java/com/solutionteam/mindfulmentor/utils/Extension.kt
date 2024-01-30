package com.solutionteam.mindfulmentor.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Long.formatTimestamp(): String {
    val dateFormat = SimpleDateFormat("EEE dd MMM - hh:mm a", Locale.getDefault())
    val date = Date(this)
    return dateFormat.format(date)
}

fun Long.formatHoursMinutes(): String {
    val hours = this / 60
    val minutes = this % 60
    return String.format("%02d:%02d", hours, minutes)
}

fun Long.isLessThanXMinutes(reminder: Int = 10): Boolean {
    val currentTimestamp = System.currentTimeMillis()
    val differenceMillis = this - currentTimestamp
    val differenceMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceMillis)

    return differenceMinutes <= reminder
}


