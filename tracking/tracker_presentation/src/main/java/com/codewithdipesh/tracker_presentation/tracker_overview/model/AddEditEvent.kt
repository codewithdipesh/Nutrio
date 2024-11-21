package com.codewithdipesh.tracker_presentation.tracker_overview.model

import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit

sealed class AddEditEvent{
    object OnBackNavigate : AddEditEvent()
    object OnToggleMeal : AddEditEvent()
    data class OnClickMeal(val mealType: MealType): AddEditEvent()
    object OnToggleSize : AddEditEvent()
    data class OnClickSize(val size: Int): AddEditEvent()
    object OnToggleUnit : AddEditEvent()
    data class OnSelectUnit(val unit : Unit) : AddEditEvent()
    data class OnSave(val food : TrackedFood) : AddEditEvent()
}
