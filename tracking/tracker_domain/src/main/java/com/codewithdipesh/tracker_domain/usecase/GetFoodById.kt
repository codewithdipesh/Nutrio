package com.codewithdipesh.tracker_domain.usecase

import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow

class GetFoodById(
    private val repository: TrackerRepository
) {
    operator  fun invoke(
        id : Int
    ): Flow<TrackedFood> {
        if(id == -1) throw IllegalArgumentException("Id cannot be null")
        val food = repository.getFoodById(id)
        if(food == null) throw IllegalArgumentException("Food not found")
        return food
    }
}