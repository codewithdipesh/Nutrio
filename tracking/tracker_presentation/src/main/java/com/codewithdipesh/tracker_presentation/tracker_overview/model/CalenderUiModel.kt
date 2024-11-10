package com.codewithdipesh.tracker_presentation.tracker_overview.model

import java.time.LocalDate

data class CalenderUiModel(
    val selectedDate : LocalDate = LocalDate.now(),
    val startDate : LocalDate = LocalDate.now(),
    val listofShownDates : List<LocalDate> = emptyList()
)
