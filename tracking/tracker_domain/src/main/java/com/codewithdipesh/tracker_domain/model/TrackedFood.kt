package com.codewithdipesh.tracker_domain.model

import java.time.LocalDate
import kotlin.math.roundToInt


data class TrackedFood(
    val name: String,
    private val _carbs: Double,
    private val _protein: Double,
    private val _fat: Double,
    val mealType: MealType,
    val amount: Int,
    private val _fiber: Double,
    val unit: Unit,
    val date: LocalDate,
     val calories: Int,
    val id: Int? = null
){
    val carbs: Double get() = ((_carbs * 10.0).roundToInt() / 10.0)
    val protein: Double get() = ((_protein * 10.0).roundToInt() / 10.0)
    val fat: Double get() = ((_fat * 10.0).roundToInt() / 10.0)
    val fiber: Double get() = ((_fiber * 10.0).roundToInt() / 10.0)
}
