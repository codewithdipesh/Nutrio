package com.codewithdipesh.core.domain.model

data class UserInfo(
    val gender : Gender,
    val age : Int,
    val height : Float,
    val weight : Float,
    val activityLevel : ActivityLevel,
    val goalType : GoalType,
    val weightPace : WeightPace,
    val calorieGoal : Int,
    val carbRatio : Float = 50f,
    val proteinRatio : Float =20f,
    val fatRatio : Float =30f
)
