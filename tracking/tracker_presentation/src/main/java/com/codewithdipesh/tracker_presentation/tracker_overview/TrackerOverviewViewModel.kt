package com.codewithdipesh.tracker_presentation.tracker_overview

import androidx.lifecycle.ViewModel
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) :ViewModel(){

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent){
        when(event){
            is TrackerOverviewEvent.OnAddFoodClick -> {

            }
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {

            }
            TrackerOverviewEvent.OnNextDayClick -> {

            }
            TrackerOverviewEvent.OnPreviousDayClick -> {

            }
            is TrackerOverviewEvent.OnToggleMealClick -> {

            }
        }
    }


}