package com.codewithdipesh.tracker_domain.usecase

import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository

class SearchFood (
    private val repository: TrackerRepository
){
    suspend operator fun invoke(
       food : String
    ):Result<TrackableFood>{
        if(food.isBlank()){
            return Result.failure(IllegalArgumentException("Food name cannot be blank"))
        }
        return repository.searchFood(food)

    }
}