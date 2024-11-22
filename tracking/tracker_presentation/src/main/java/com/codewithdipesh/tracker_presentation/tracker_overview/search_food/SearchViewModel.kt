package com.codewithdipesh.tracker_presentation.tracker_overview.search_food


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.Unit
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUIState
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUsecases : TrackerUseCases
) : ViewModel() {

    var state by mutableStateOf((SearchUIState()))

    var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun setMealType(mealType: MealType){
        state =state.copy(
            mealType = mealType
        )
    }

    fun setDate(date: LocalDate){
        state =state.copy(
            date = date
        )
    }

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnBackNavigate -> {
                _uiEvent.trySend(
                    UiEvent.NavigateUp
                )
            }
            is SearchUiEvent.OnSearch -> {
                Log.d("SEARCH",state.searchQuery)
                state = state.copy(
                    isLoading = true
                )
                searchFood(state.searchQuery)
            }
            is SearchUiEvent.onAddFoodClick -> {
                addFood(event.trackableFood)
                _uiEvent.trySend(
                    UiEvent.showSnackBar("Food Added Successfully")
                )
            }
            is SearchUiEvent.onFoodClick -> {
                _uiEvent.trySend(
                    UiEvent.Navigate(
                        Route.ADD_EDIT_FOOD
                        +"/-1"
                        +"/${event.trackableFood}"
                        +"/${state.mealType}"
                    )
                )
            }
            is SearchUiEvent.OnQueryChange -> {
                state = state.copy(
                    searchQuery = event.query
                )
            }

            is SearchUiEvent.OnClear -> {
                state = state.copy(
                    searchQuery = ""
                )
            }

            is SearchUiEvent.OnMealTypeChange -> {
                state = state.copy(
                    mealType = event.mealType
                )
            }

            else -> {}
        }
    }

    fun clearResult(){
        state = state.copy(
            result = null
        )
    }

    private fun searchFood(query: String) {
        viewModelScope.launch {
            val result = trackerUsecases.searchFood(query)
            Log.d("SEARCH_RESULT",result.toString())
            result.onFailure {throwable->
                state = state.copy(
                    isLoading = false
                )
                _uiEvent.trySend(
                        UiEvent.showSnackBar(
                            msg = throwable.message ?:"Unknown Error"
                        )
                    )

            }
            Log.d("SEARCH_RESULT",result.toString())
            result.onSuccess {
                state = state.copy(
                    isLoading = false,
                    result = it
                )
            }
        }
    }

    private fun addFood(trackableFood: TrackableFood){
        viewModelScope.launch {
            trackerUsecases.trackFood(
                food = trackableFood,
                unit = Unit.Gm100,
                amount = 1.0,
                mealType = state.mealType,
                date = state.date
            )
        }
    }



}