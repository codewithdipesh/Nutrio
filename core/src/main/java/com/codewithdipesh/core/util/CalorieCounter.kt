package com.codewithdipesh.core.util

import com.codewithdipesh.core.domain.model.ActivityLevel
import com.codewithdipesh.core.domain.model.Gender
import com.codewithdipesh.core.domain.model.GoalType
import com.codewithdipesh.core.domain.model.WeightPace


fun CalorieCounter(
    weightKg: Float,
    heightInch: Float,
    age: Int,
    gender: Gender,
    activityLevel: ActivityLevel,
    goalType: GoalType,
    weightPace: WeightPace
):Int {
    val heightCm =  heightInch * 2.54

    val bmr = if (gender == Gender.Male) {
        (10 * weightKg) + (6.25 * heightCm) - (5 * age) + 5
    } else {
        (10 * weightKg) + (6.25 * heightCm) - (5 * age) - 161
    }

    val activityMultiplier = when (activityLevel) {
        ActivityLevel.Active -> 1.2
        ActivityLevel.Light -> 1.375
        ActivityLevel.Moderate -> 1.55
        ActivityLevel.Sedentary -> 1.725
        ActivityLevel.VeryActive -> 1.9
    }

    val maintenanceCalories = bmr * activityMultiplier

    val pacePerWeekKg = when (weightPace) {
        WeightPace.Moderate -> 0.25f
        WeightPace.Extreme -> 0.5f
        WeightPace.Null -> 0.0f
    }

    val targetCalories = when (goalType) {
        GoalType.GainWeight -> {
            val calorieSurplus = (pacePerWeekKg * 7700) / 7 // 7700 kcal = 1kg
            maintenanceCalories + calorieSurplus
        }
        GoalType.LoseWeight -> {
            val calorieDeficit = (pacePerWeekKg * 7700) / 7
            maintenanceCalories - calorieDeficit
        }
        GoalType.KeepWeight -> maintenanceCalories
    }


    return targetCalories.toInt()
}