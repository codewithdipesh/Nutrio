package com.codewithdipesh.tracker_presentation.tracker_overview

import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

data class TrackerOverviewState(
    val totalCarbs : Int = 0,
    val totalProteins : Int = 0,
    val totalFats : Int = 0,
    val totalCalories : Int = 0,
    val calorieGoal : Int = 0,
    val carbsGoal : Int = 0,
    val proteinsGoal : Int = 0,
    val fatsGoal : Int = 0,
    val date : LocalDate = LocalDate.now(),
    val trackedFoods : List<TrackedFood> = emptyList(),
    val meals : List<Meal> = defaultMeals
)
