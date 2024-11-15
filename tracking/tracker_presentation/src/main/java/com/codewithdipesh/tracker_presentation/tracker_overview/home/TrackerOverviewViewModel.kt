package com.codewithdipesh.tracker_presentation.tracker_overview.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import com.codewithdipesh.tracker_presentation.tracker_overview.model.CalenderUiModel
import com.codewithdipesh.tracker_presentation.tracker_overview.model.TrackerOverviewEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.model.TrackerOverviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) :ViewModel(){

    var state by mutableStateOf(TrackerOverviewState())

    var calendarState by mutableStateOf(CalenderUiModel())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodForDateJob : Job? = null

    init {
        preferences.saveShouldShowOnboarding(false)
        refreshFoods()
        getCalendarData(state.date)
    }

    fun onEvent(event: TrackerOverviewEvent){
        when(event){
            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                       UiEvent.Navigate(
                           route = Route.SEARCH
                           +"/${event.mealType.name}"
                           +"/${state.date}"
                       )
                    )
                }
            }
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFoods()
                }
            }
            TrackerOverviewEvent.OnNextDayClick -> {
                val newDate = state.date.plusDays(1)
                state = state.copy(
                    date = newDate
                )
                calendarState =calendarState.copy(
                    selectedDate = newDate,
                    listofShownDates = getDatesForCurrentWeek(newDate)
                )
                refreshFoods()

            }
            TrackerOverviewEvent.OnPreviousDayClick -> {
                val newDate = state.date.minusDays(1)
                state = state.copy(
                    date = newDate
                )
                calendarState =calendarState.copy(
                    selectedDate = newDate,
                    listofShownDates = getDatesForCurrentWeek(newDate)
                )
                refreshFoods()
            }
            is TrackerOverviewEvent.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if(it.mealType == event.meal.mealType ){
                            it.copy(
                                isExpanded = !it.isExpanded
                            )
                        }else it
                    }
                )
            }
            TrackerOverviewEvent.OnNextWeekClick -> {
                val updatedStartDate = calendarState.startDate.plusWeeks(1)
                calendarState = calendarState.copy(
                    startDate = updatedStartDate,
                    listofShownDates = getDatesForCurrentWeek(updatedStartDate)
                )
            }
            TrackerOverviewEvent.OnPreviousWeekClick -> {
                val updatedStartDate = calendarState.startDate.minusWeeks(1)
                calendarState = calendarState.copy(
                    startDate = updatedStartDate,
                    listofShownDates = getDatesForCurrentWeek(updatedStartDate)
                )
            }

            is TrackerOverviewEvent.OnDateSelect -> {
                state = state.copy(
                    date = event.date
                )
                calendarState = calendarState.copy(
                    selectedDate = event.date
                )
                refreshFoods()
            }

        }
    }

    private fun refreshFoods(){
        getFoodForDateJob?.cancel() // cancelling existing job ( coroutines)
        getFoodForDateJob  = trackerUseCases
            .getFoodsForDate(state.date)
            .onEach { foods->
                val nutrientsResult = trackerUseCases.calculateMealNutriments(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProteins = nutrientsResult.totalProtein,
                    totalFats = nutrientsResult.totalFat,
                    totalFiber = nutrientsResult.totalFiber,
                    totalCalories = nutrientsResult.totalCalories,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinsGoal = nutrientsResult.proteinsGoal,
                    fatsGoal = nutrientsResult.fatsGoal,
                    calorieGoal = nutrientsResult.caloriesGoal,
                    trackedFoods = foods,
                    meals = state.meals.map {
                         val mealDetails = nutrientsResult.mealNutrimens[it.mealType]
                             ?: return@map it.copy(
                                 calories = 0
                             )
                         it.copy(
                             calories = mealDetails.calories
                         )

                    }
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getCalendarData(date: LocalDate){
        val showingDates = getDatesForCurrentWeek(date)
        calendarState = calendarState.copy(
            startDate = showingDates.first(),
            listofShownDates = showingDates
        )
    }

    private fun getDatesForCurrentWeek(date : LocalDate):List<LocalDate>{
        val startDate = date.with(DayOfWeek.MONDAY)
        return (0..6).map { startDate.plusDays(it.toLong()) }
    }


}