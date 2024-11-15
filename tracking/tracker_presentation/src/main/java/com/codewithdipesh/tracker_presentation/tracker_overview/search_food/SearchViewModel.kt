package com.codewithdipesh.tracker_presentation.tracker_overview.search_food


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.Unit
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUIState
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUsecases : TrackerUseCases
) : ViewModel() {

    var state by mutableStateOf((SearchUIState()))

    var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: SearchUiEvent) {
        when (event) {
            SearchUiEvent.OnBackNavigate -> {
                _uiEvent.trySend(
                    UiEvent.NavigateUp
                )
            }
            SearchUiEvent.OnSearch -> {
                Log.d("SEARCH",state.searchQuery)
                state = state.copy(
                    isLoading = true
                )
                searchFood(state.searchQuery)
            }
            is SearchUiEvent.onAddFoodClick -> {
                addFood(event.trackableFood)
            }
            is SearchUiEvent.onFoodClick -> {
                _uiEvent.trySend(
                    UiEvent.Navigate(//TODO
                        Route.ADD_EDIT_FOOD
                    )
                )
            }
            is SearchUiEvent.OnQueryChange -> {
                state = state.copy(
                    searchQuery = event.query
                )
            }

            SearchUiEvent.OnClear -> {
                state = state.copy(
                    searchQuery = ""
                )
            }

            is SearchUiEvent.OnMealTypeChange -> {
                state = state.copy(
                    mealType = event.mealType
                )
            }
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
                amount = 1,
                mealType = state.mealType,
                date = state.date
            )
        }
    }

}