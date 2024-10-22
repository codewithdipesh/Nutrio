package com.codewithdipesh.onboarding_presentation.activity_level

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.domain.model.ActivityLevel
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val preferences: Preferences
) :ViewModel() {

var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivitySelect(activity:ActivityLevel){
        selectedActivityLevel = activity
    }

    fun onNextClick(){
        preferences.saveActivityLevel(selectedActivityLevel)
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
        }
    }

    fun onBackClick(){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }

}