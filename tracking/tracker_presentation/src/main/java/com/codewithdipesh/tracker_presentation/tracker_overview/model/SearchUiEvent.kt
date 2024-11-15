package com.codewithdipesh.tracker_presentation.tracker_overview.model

import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood

sealed class SearchUiEvent(){
    object OnBackNavigate : SearchUiEvent()
    object OnSearch : SearchUiEvent()
    object OnClear : SearchUiEvent()
    data class onAddFoodClick(val trackableFood: TrackableFood) : SearchUiEvent()
    data class onFoodClick(val trackableFood: TrackableFood) : SearchUiEvent()
    data class OnQueryChange(val query : String) : SearchUiEvent()
}
