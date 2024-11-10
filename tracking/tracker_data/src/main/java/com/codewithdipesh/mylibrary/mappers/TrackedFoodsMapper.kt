package com.codewithdipesh.mylibrary.mappers

import com.codewithdipesh.mylibrary.local.entity.TrackedFoodEntity
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood{
   return TrackedFood(
       name = name,
       _carbs=carbs,
       _protein=protein,
       _fat = fat,
       unit = Unit.fromString(unit),
       mealType = MealType.fromString(type),
       amount = amount,
       _fiber = fibers,
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
        fibers = fiber,
        unit = unit.name,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}