package com.codewithdipesh.mylibrary.mappers

import com.codewithdipesh.mylibrary.remote.dto.Ingredient
import com.codewithdipesh.mylibrary.remote.dto.SearchDto
import com.codewithdipesh.tracker_domain.model.Nutrients
import com.codewithdipesh.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt
import com.codewithdipesh.tracker_domain.model.Unit

fun List<Ingredient>.toUnitNutrition() : Map<Unit, Nutrients>{
    val ingredientList = this
    val nutrients = HashMap<Unit, Nutrients>()
    for (ingredient in ingredientList){
        val parsed = ingredient.parsed[0]

        // teaspoon -> Unit.TeaSpoon
        val unitText = parsed.measure
        val unit = Unit.fromString(unitText!!)

        val carb = parsed.nutrients!!.carbs!!.value
        val protein = parsed.nutrients!!.protein!!.value
        val fat = parsed.nutrients.fat!!.value
        val calorie = parsed.nutrients!!.calories!!.value
        val fiber = parsed.nutrients.fibers!!.value

        val nutrientValues = Nutrients(calorie, carb!!, protein, fat, fiber)
        nutrients[unit] = nutrientValues
    }
    return  nutrients
}

fun SearchDto.toFoodName(): String? {
    return this.ingredients!![0].parsed[0].foodMatch
}