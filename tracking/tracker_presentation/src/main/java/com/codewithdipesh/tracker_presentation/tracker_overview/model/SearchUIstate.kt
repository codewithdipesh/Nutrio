package com.codewithdipesh.tracker_presentation.tracker_overview.model

import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

data class SearchUIState(
    val mealType : MealType = MealType.Breakfast,
    val isLoading : Boolean = false,
    val searchQuery : String = "",
    val result : TrackableFood? = null,
    val date : LocalDate =LocalDate.now()
)
