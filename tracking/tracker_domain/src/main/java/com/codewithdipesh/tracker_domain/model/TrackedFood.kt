package com.codewithdipesh.tracker_domain.model

import java.time.LocalDate
import kotlin.math.roundToInt


data class TrackedFood(
    val name: String = "",
    private val _carbs: Double =0.0,
    private val _protein: Double=0.0,
    private val _fat: Double=0.0,
    val mealType: MealType=MealType.Breakfast,
    val amount: Double=1.0,
    private val _fiber: Double=0.0,
    val unit: Unit=Unit.Gm100,
    val date: LocalDate =LocalDate.now(),
     val calories: Int=0,
    val id: Int? = null,
    val nutrients: Map<Unit,Nutrients> = emptyMap()
){
    val carbs: Double get() = ((_carbs * 10.0).roundToInt() / 10.0)
    val protein: Double get() = ((_protein * 10.0).roundToInt() / 10.0)
    val fat: Double get() = ((_fat * 10.0).roundToInt() / 10.0)
    val fiber: Double get() = ((_fiber * 10.0).roundToInt() / 10.0)
}
