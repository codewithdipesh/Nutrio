package com.codewithdipesh.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences
) :ViewModel() {

    var selectedHeightInInch by mutableStateOf(60.5f)
        private set


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightSelect(inputHeight:Float){
        selectedHeightInInch = inputHeight
    }

    fun onNextClick(){
        preferences.saveHeight(selectedHeightInInch)
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }

    fun onBackClick(){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }


}