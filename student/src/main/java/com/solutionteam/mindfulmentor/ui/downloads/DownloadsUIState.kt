package com.solutionteam.mindfulmentor.ui.downloads

import com.solutionteam.mindfulmentor.data.entity.Download
import com.solutionteam.mindfulmentor.data.entity.Subject
import com.solutionteam.mindfulmentor.ui.home.SubjectDetailsUiState
import com.solutionteam.mindfulmentor.ui.home.toSubjectUiState
import com.solutionteam.mindfulmentor.ui.mentor.MeetingUIState
import com.solutionteam.mindfulmentor.ui.mentor.SummeryDetailsUIState
import com.solutionteam.mindfulmentor.ui.mentor.toListUIState
import com.solutionteam.mindfulmentor.ui.mentor.toMeetingListUIState

data class DownloadsUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val downloadDetails: DownloadDetailsUIState = DownloadDetailsUIState(),
)

data class DownloadDetailsUIState(
    val summaryNumber: String = "",
    val videoNumber: String = "",
    val meetingNumber: String = "",
    val subjectList: List<SubjectDetailsUiState> = emptyList(),
    val summaryList: List<SummeryDetailsUIState> = emptyList(),
    val videoList: List<SummeryDetailsUIState> = emptyList(),
    val meetingList: List<MeetingUIState> = emptyList()
)

fun Download.toUIState(): DownloadDetailsUIState {
    return DownloadDetailsUIState(
        summaryNumber = summariesNumber,
        videoNumber = videoNumber,
        meetingNumber = meetingNumber,
        subjectList = subjects.toSubjectUiState(),
        summaryList = summaries.filter { it.isBuy }.toListUIState(),
        videoList = video.filter { it.isBuy }.toListUIState(),
        meetingList = meeting.filter { it.isBook }.toMeetingListUIState()
    )
}