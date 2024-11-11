package com.codewithdipesh.tracker_presentation.tracker_overview.model

import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

sealed class TrackerOverviewEvent{
    object OnNextDayClick : TrackerOverviewEvent()
    object OnPreviousDayClick : TrackerOverviewEvent()
    object OnNextWeekClick : TrackerOverviewEvent()
    object OnPreviousWeekClick : TrackerOverviewEvent()
    object OnBackNavigate : TrackerOverviewEvent()
    data class OnDateSelect(val date : LocalDate) : TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal) : TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerOverviewEvent()
    data class OnAddFoodClick(val meal: Meal) : TrackerOverviewEvent()
}