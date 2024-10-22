package com.codewithdipesh.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core.util.calculateBmi
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

    var selectedWeight by mutableStateOf(57.5f) //57.5 +2.5 => 60 kg  , bcz Ui taking 2.5 kg extra
        private set

    var bmi by mutableStateOf(23.4f)
        private set


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightSelect(inputWeight:Float){
        selectedWeight = inputWeight
        viewModelScope.launch {
            bmi = getBmi()
        }
    }
    fun onNextClick(){
        preferences.saveWeight(selectedWeight)
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }

    fun onBackClick(){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }

     private suspend fun getBmi() : Float{
        val userInfo = preferences.loadUserInfo()
        bmi = calculateBmi(selectedWeight,userInfo.height,userInfo.age,userInfo.gender)
        return bmi

    }
}