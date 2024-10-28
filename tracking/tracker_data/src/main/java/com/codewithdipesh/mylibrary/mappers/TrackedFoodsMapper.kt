package com.codewithdipesh.mylibrary.mappers

import com.codewithdipesh.mylibrary.local.entity.TrackedFoodEntity
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood{
   return TrackedFood(
       name = name,
       carbs=carbs,
       protein=protein,
       fat = fat,
       imageUrl =imageUrl,
       mealType = MealType.fromString(type),
       amount = amount,
       date = LocalDate.of(year, month, dayOfMonth),
       calories = calories,
       id = id
   )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity{
    return TrackedFoodEntity(
        name = name,
        carbs=carbs,
        protein=protein,
        fat = fat,
        imageUrl =imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}