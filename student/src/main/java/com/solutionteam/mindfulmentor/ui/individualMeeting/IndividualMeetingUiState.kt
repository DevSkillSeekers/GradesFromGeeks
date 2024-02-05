package com.solutionteam.mindfulmentor.ui.individualMeeting

import com.solutionteam.mindfulmentor.data.entity.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class IndividualMeetingUIState(
    val availableDates: List<AvailableDateUiState> = emptyList(),

    val note: String = "",
    val showBottomSheet: Boolean = false,
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
    val day: String = "",
    val isSelected: Boolean = false
)


//region Mapper

fun Date.toDateUiState() = AvailableDateUiState(
    day = day,
    times = times.toTimeUiState()
)

fun List<Date>.toDateUiState() = map { it.toDateUiState() }
fun Long.toTimeUiState() = TimeUiState(
    id = "$this",
    time = "${this.getFormattedTime()} - ${this.getFormattedTime(1)}",
    day = this.getFormattedDay()
)

fun List<Long>.toTimeUiState() = map { it.toTimeUiState() }

private fun Long.getFormattedTime(addHours: Int = 0): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.HOUR_OF_DAY, addHours)
    val timeFormat = SimpleDateFormat("HH:mm", Locale.US)
    return timeFormat.format(calendar.time)
}

private fun Long.getFormattedDay(): String {
    val dateFormat = SimpleDateFormat("EEE d, MMM yyyy", Locale.US)
    return dateFormat.format(this)
}


fun AvailableDateUiState.setSelectTime(selectTimeId: String? = null) = AvailableDateUiState(
    times = times.map {
        return@map if (selectTimeId == null) {
            it.copy(isSelected = false)
        } else if (it.id == selectTimeId) {
            it.copy(isSelected = true)
        } else {
            it.copy(isSelected = false)
        }

    }
)
//endregion
