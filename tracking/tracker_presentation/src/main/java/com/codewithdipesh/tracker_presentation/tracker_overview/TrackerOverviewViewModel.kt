package com.codewithdipesh.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) :ViewModel(){

    var state by mutableStateOf(TrackerOverviewState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodForDateJob : Job? = null

    init {
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent){
        when(event){
            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                       UiEvent.Navigate(
                           route = Route.SEARCH
                           +"/${event.meal.mealType.name}"
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
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFoods()

            }
            TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
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


}