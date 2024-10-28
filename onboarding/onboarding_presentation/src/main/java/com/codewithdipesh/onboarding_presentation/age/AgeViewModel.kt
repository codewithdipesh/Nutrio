package com.codewithdipesh.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.domain.model.Gender
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences
) :ViewModel() {

    var age by mutableStateOf(20)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeSelect(inputAge: Int){
        age = inputAge
    }
    fun onNextClick(){
        viewModelScope.launch {
            preferences.saveAge(age)
            _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
        }
    }
    fun onBackClick(){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }

    }

}