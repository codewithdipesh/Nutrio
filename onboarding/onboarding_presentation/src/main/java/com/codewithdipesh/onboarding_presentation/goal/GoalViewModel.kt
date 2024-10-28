package com.codewithdipesh.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.domain.model.GoalType
import com.codewithdipesh.core.domain.model.WeightPace
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {

    var selectedGoalType by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalSelect(type: GoalType) {
        selectedGoalType = type
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoalType)
            if(selectedGoalType is GoalType.KeepWeight){
                preferences.saveWeightPace(WeightPace.Null)
                _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
            }else{
                _uiEvent.send(UiEvent.Navigate(Route.WEIGHTPACE))
            }

        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }

    }
}