package com.codewithdipesh.tracker_domain.usecase

import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlin.math.roundToInt

class CalculateMealNutriments (
    private val preferences: Preferences
) {

    suspend operator fun invoke(trackedFoods : List<TrackedFood>):Result{
        val allNutriments = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value //list of all foods at particular meal type
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    proteins = foods.sumOf { it.protein },
                    fats = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    fiber = foods.sumOf { it.fiber },
                    mealType = type
                )
            }
        val totalCarbs = allNutriments.values.sumOf { it.carbs }
        val totalProteins = allNutriments.values.sumOf { it.proteins }
        val totalFats = allNutriments.values.sumOf { it.fats }
        val totalFibers = (allNutriments.values.sumOf { it.fiber }).toInt()
        val totalCalories = allNutriments.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()

        val caloriesGoal =  userInfo.calorieGoal

        val carbsGoalFormatted = ((caloriesGoal * (userInfo.carbRatio/100)) /4f).toInt()

        val proteinsGoalFormatted = ((caloriesGoal * (userInfo.proteinRatio/100)) /4f).toInt()

        val fatsGoalFormatted = ((caloriesGoal * (userInfo.fatRatio/100)) /9f).toInt()


        return Result(
            carbsGoal = carbsGoalFormatted,
            proteinsGoal = proteinsGoalFormatted,
            fatsGoal = fatsGoalFormatted,
            caloriesGoal = caloriesGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProteins,
            totalFat = totalFats,
            totalFiber = totalFibers,
            totalCalories = totalCalories,
            mealNutrimens = allNutriments
        )
    }

    data class MealNutrients(
        val carbs : Double,
        val proteins : Double,
        val fats : Double,
        val fiber : Double,
        val calories : Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal : Int,
        val proteinsGoal : Int,
        val fatsGoal : Int,
        val caloriesGoal : Int,
        val totalCarbs : Double,
        val totalProtein: Double,
        val totalFat : Double,
        val totalCalories : Int,
        val totalFiber : Int,
        val mealNutrimens : Map<MealType,MealNutrients>
    )
}