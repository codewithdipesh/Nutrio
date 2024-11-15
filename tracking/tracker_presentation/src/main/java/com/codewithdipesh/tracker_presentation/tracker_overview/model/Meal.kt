package com.codewithdipesh.tracker_presentation.tracker_overview.model


import com.codewithdipesh.core.util.UiText
import com.codewithdipesh.core.R
import com.codewithdipesh.tracker_domain.model.MealType

data class Meal(
    val name : UiText,
    val calories:Int = 0,
    val isExpanded:Boolean = false,
    val mealType: MealType
)


val defaultMeals = listOf(
  Meal(
      name = UiText.StringResource(R.string.breakfast),
      mealType = MealType.Breakfast
  ),
    Meal(
        name = UiText.StringResource(R.string.lunch),
        mealType = MealType.Lunch
    ),
    Meal(
        name = UiText.StringResource(R.string.dinner),
        mealType = MealType.Dinner
    ),
    Meal(
        name = UiText.StringResource(R.string.snacks),
        mealType = MealType.Snack
    )
)
