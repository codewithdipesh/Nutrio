package com.codewithdipesh.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.onboarding_presentation.height.LengthUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private  val preferences: Preferences
):ViewModel() {

    var selectedWeight by mutableStateOf(67f)
        private set


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightSelect(inputWeight:Float){
        selectedWeight = inputWeight
    }
    fun onNextClick(){
        preferences.saveWeight(selectedWeight)
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
        }
    }

    fun onBackClick(){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }

    }
}