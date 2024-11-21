package com.codewithdipesh.tracker_presentation.tracker_overview.model

import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.Nutrients
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit
import java.time.LocalDate

data class AddEditState(
    val food: TrackedFood =TrackedFood(),
    val carb :Double = 0.0,
    val protein : Double = 0.0,
    val fat :Double = 0.0,
    val amount :Int = 0,
    val fiber :Double = 0.0,
    val unit :Unit = Unit.Gm100,
    val calories :Int = 0,
    val mealType : MealType = MealType.Breakfast,
    val date :LocalDate= LocalDate.now(),
    val id :Int = -1,
    val isSizeExpanded :Boolean = false,
    val isMealExpanded :Boolean = false,
    val isUnitExpanded :Boolean = false
)