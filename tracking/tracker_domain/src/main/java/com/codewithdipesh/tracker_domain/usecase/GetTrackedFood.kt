package com.codewithdipesh.tracker_domain.usecase

import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetTrackedFood (
    private val repository: TrackerRepository
){
    operator  fun invoke(
        food : TrackableFood,
        mealType: MealType
    ): TrackedFood {
        return repository.getTrackedFood(food, mealType)
    }
}