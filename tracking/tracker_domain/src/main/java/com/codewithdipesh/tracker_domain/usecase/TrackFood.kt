package com.codewithdipesh.tracker_domain.usecase


import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood (
    private val repository: TrackerRepository
){
    suspend operator fun invoke(
       food: TrackableFood,
       unit : Unit,
       amount:Double,
       mealType: MealType,
       date: LocalDate
    ){

        repository.insertTrackedFood(
            TrackedFood(
              name = food.name,
              _carbs = (food.nutrients[unit]!!.carbs) * amount,
              _fiber = food.nutrients[unit]!!.fiber * amount,
              _protein = food.nutrients[unit]!!.protein * amount,
              _fat = food.nutrients[unit]!!.fat * amount,
              calories = (food.nutrients[unit]!!.calories * amount ).roundToInt(),
              amount = amount,
              unit = unit,
              mealType = mealType,
              nutrients = food.nutrients,
              date = date
            )
        )
    }
    suspend operator fun invoke(
        food: TrackedFood,
        unit : Unit,
        amount:Double,
        mealType: MealType,
        date: LocalDate
    ){
        repository.insertTrackedFood(food.copy(
            _carbs = (food.nutrients[unit]!!.carbs) * amount,
            _fiber = food.nutrients[unit]!!.fiber * amount,
            _protein = food.nutrients[unit]!!.protein * amount,
            _fat = food.nutrients[unit]!!.fat * amount,
            calories = (food.nutrients[unit]!!.calories * amount ).roundToInt(),
            amount = amount,
            unit = unit,
            mealType = mealType,
            date = date
        ))
    }
}