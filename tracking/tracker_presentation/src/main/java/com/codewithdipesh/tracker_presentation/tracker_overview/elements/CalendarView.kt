package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewViewModel

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    onCalendarClose : ()-> Unit,
    viewModel: TrackerOverviewViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,  // Remove the ripple effect
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCalendarClose()  // Close calendar on background click
            }
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .wrapContentHeight()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    //so the inner click will not close the calendar
                }
        ){
            CalendarRow(
                listofShownDates = viewModel.calendarState.listofShownDates,
                selectedDate = viewModel.calendarState.selectedDate,
                onSelect = {
                    viewModel.onEvent(TrackerOverviewEvent.OnDateSelect(it))
                    onCalendarClose()
                },
                onPreviousWeek = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousWeekClick)
                },
                onNextWeek = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextWeekClick)
                }
            )
        }
    }

}