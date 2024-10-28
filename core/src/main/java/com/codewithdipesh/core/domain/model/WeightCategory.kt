package com.codewithdipesh.core.domain.model

sealed class WeightCategory(val name : String) {
    object Underweight : WeightCategory("Underweight")
    object Normal : WeightCategory("Normal")
    object OverWeight : WeightCategory("OverWeight")
    object Obesse : WeightCategory("Obesse")
}
