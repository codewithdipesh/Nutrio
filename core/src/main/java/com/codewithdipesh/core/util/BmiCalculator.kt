package com.codewithdipesh.core.util

import com.codewithdipesh.core.domain.model.Gender
import com.codewithdipesh.core.domain.model.WeightCategory

fun calculateBmi(weight: Float, height: Float, age: Int, gender: Gender): Float {
    val heightInMeters = height * 0.0254f
    val bmi = weight / (heightInMeters * heightInMeters)

    val ageAdjustment = if (age < 18) {
        when (gender) {
            Gender.Male -> 0.95f
            Gender.Female -> 0.9f
        }
    } else {
        1f // Adults
    }

    // Apply adjustments
    val adjustedBmi = bmi * ageAdjustment

    return String.format("%.1f", adjustedBmi).toFloat()
}

fun getBmiCategory(bmi: Float): WeightCategory {
    return when {
        bmi < 18.5f -> WeightCategory.Underweight
        bmi in 18.5f..24.9f -> WeightCategory.Normal
        bmi in 25f..29.9f -> WeightCategory.OverWeight
        else -> WeightCategory.Obesse
    }
}
