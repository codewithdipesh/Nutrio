package com.codewithdipesh.mylibrary.mappers

import android.util.Log
import com.codewithdipesh.mylibrary.remote.dto.Ingredient
import com.codewithdipesh.mylibrary.remote.dto.SearchDto
import com.codewithdipesh.tracker_domain.model.Nutrients
import com.codewithdipesh.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt
import com.codewithdipesh.tracker_domain.model.Unit

fun List<Ingredient?>?.toUnitNutrition(): Map<Unit, Nutrients> {
    val nutrients = HashMap<Unit, Nutrients>()
    this?.forEach { ingredient ->
        ingredient?.parsed?.firstOrNull()?.let { parsed ->
            val unitText = parsed.measure ?: "Unknown"
            val unit = Unit.fromString(unitText)
            Log.d("SEARCH_MAPPER",parsed.nutrients?.carbs?.value.toString())
            val carb = parsed.nutrients?.carbs?.value ?: 0.0
            Log.d("SEARCH_MAPPER",parsed.nutrients?.carbs?.value.toString())
            val protein = parsed.nutrients?.protein?.value ?: 0.0
            Log.d("SEARCH_MAPPER",parsed.nutrients?.carbs?.value.toString())
            val fat = parsed.nutrients?.fat?.value ?: 0.0
            Log.d("SEARCH_MAPPER",parsed.nutrients?.carbs?.value.toString())
            val calorie = parsed.nutrients?.calories?.value ?: 0.0
            Log.d("SEARCH_MAPPER",parsed.nutrients?.carbs?.value.toString())
            val fiber = parsed.nutrients?.fibers?.value ?: 0.0

            val nutrientValues = Nutrients(calorie, carb, protein, fat, fiber)
            nutrients[unit] = nutrientValues
        }
    }
    return nutrients
}

fun SearchDto.toFoodName(): String? {
    return this.ingredients?.firstOrNull()?.parsed?.firstOrNull()?.foodMatch ?: "Unknown Food"
}