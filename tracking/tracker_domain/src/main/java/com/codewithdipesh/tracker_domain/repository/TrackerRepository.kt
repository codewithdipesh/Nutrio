package com.codewithdipesh.tracker_domain.repository

import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
       food : String
    ): Result<TrackableFood>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getsFoodForDate(localDate: LocalDate) : Flow<List<TrackedFood>>

    fun getFoodById(id:Int) : Flow<TrackedFood>

    fun getTrackedFood(food:TrackableFood,mealType: MealType) : TrackedFood
}