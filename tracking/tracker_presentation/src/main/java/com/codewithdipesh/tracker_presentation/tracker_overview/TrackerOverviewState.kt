package com.codewithdipesh.tracker_presentation.tracker_overview

import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

data class TrackerOverviewState(
    val totalCarbs : Double = 0.0,
    val totalProteins : Double = 0.0,
    val totalFats : Double = 0.0,
    val totalFiber : Double = 0.0,
    val totalCalories : Int = 0,
    val calorieGoal : Int = 0,
    val carbsGoal : Double = 0.0,
    val proteinsGoal : Double = 0.0,
    val fatsGoal : Double = 0.0,
    val date : LocalDate = LocalDate.now(),
    val trackedFoods : List<TrackedFood> = emptyList(),
    val meals : List<Meal> = defaultMeals
)
