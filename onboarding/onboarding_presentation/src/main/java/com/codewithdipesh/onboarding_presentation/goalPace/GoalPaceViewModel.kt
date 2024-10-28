package com.codewithdipesh.onboarding_presentation.goalPace

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.domain.model.WeightPace
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalPaceViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {

    var selectedGoalPace by mutableStateOf<WeightPace>(WeightPace.Moderate)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalPaceSelect(type: WeightPace) {
        selectedGoalPace = type
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveWeightPace(selectedGoalPace)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }

    }
}