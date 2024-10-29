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
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    proteins = foods.sumOf { it.protein },
                    fats = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        val totalCarbs = allNutriments.values.sumOf { it.carbs }
        val totalProteins = allNutriments.values.sumOf { it.proteins }
        val totalFats = allNutriments.values.sumOf { it.fats }
        val totalCalories = allNutriments.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()

        val caloriesGoal =  userInfo.calorieGoal
        val carbsGoal = ((caloriesGoal * (userInfo.carbRatio/100)) /4f).roundToInt() //1g carb = 4 cal
        val proteinsGoal = ((caloriesGoal * (userInfo.proteinRatio/100)) /4f).roundToInt()
        val fatsGoal = ((caloriesGoal * (userInfo.fatRatio/100)) /9f).roundToInt() // 1g fat = 9 cal

        return Result(
            carbsGoal = carbsGoal,
            proteinsGoal = proteinsGoal,
            fatsGoal = fatsGoal,
            caloriesGoal = caloriesGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProteins,
            totalFat = totalFats,
            totalCalories = totalCalories,
            mealNutrimens = allNutriments
        )
    }

    data class MealNutrients(
        val carbs : Int,
        val proteins : Int,
        val fats : Int,
        val calories : Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal : Int,
        val proteinsGoal : Int,
        val fatsGoal : Int,
        val caloriesGoal : Int,
        val totalCarbs : Int,
        val totalProtein: Int,
        val totalFat : Int,
        val totalCalories : Int,
        val mealNutrimens : Map<MealType,MealNutrients>
    )
}