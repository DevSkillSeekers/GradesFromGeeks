package com.solutionteam.mindfulmentor.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else              -> null
}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
            window,
            window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}

fun Context.setScreenOrientation(orientation: Int) {
    val activity = this.findActivity() ?: return
    activity.requestedOrientation = orientation
    if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        hideSystemUi()
    } else {
        showSystemUi()
    }
}