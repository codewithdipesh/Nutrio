package com.codewithdipesh.tracker_domain.usecase

data class TrackerUseCases(
    val trackFood : TrackFood,
    val searchFood : SearchFood,
    val getFoodsForDate : GetFoodsByDate,
    val deleteTrackedFood : DeleteTrackFood,
    val calculateMealNutriments : CalculateMealNutriments,
    val getFoodById: GetFoodById
)