package com.solutionteam.mindfulmentor.ui.individualMeeting

import com.solutionteam.mindfulmentor.data.entity.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class IndividualMeetingUIState(
    val availableDates: List<AvailableDateUiState> = emptyList(),

    val selectedTime: TimeUiState? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


data class AvailableDateUiState(
    val day: String = "",
    val times: List<TimeUiState> = emptyList()
)

data class TimeUiState(
    val id: String = "",
    val time: String = "",
)


//region Mapper

fun Date.toDateUiState() = AvailableDateUiState(
    day = day,
    times = times.toTimeUiState()
)

fun List<Date>.toDateUiState() = map { it.toDateUiState() }
fun Long.toTimeUiState() = TimeUiState(
    id = "$this",
    time = "${this.getFormattedTime()} - ${this.getFormattedTime(1)}"
)

fun List<Long>.toTimeUiState() = map { it.toTimeUiState() }

private fun Long.getFormattedTime(addHours: Int = 0): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.HOUR_OF_DAY, addHours)
    val timeFormat = SimpleDateFormat("HH:mm", Locale.US)
    return timeFormat.format(calendar.time)
}
//endregion
