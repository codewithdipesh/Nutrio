package com.codewithdipesh.mylibrary.remote.dto

data class SearchDto(
    val calories: Int?,
    val cautions: List<Any?>,
    val co2EmissionsClass: String?,
    val cuisineType: List<String>?,
    val dietLabels: List<String>?,
    val dishType: List<String>?,
    val healthLabels: List<String>?,
    val ingredients: List<Ingredient>?,
    val mealType: List<String>?,
    val totalCO2Emissions: Double?,
    val totalDaily: TotalDaily?,
    val totalNutrients: TotalNutrients?,
    val totalNutrientsKCal: TotalNutrientsKCal?,
    val totalWeight: Double?,
    val uri: String?,
    val yield: Double?
)