package com.codewithdipesh.tracker_domain.usecase

import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsByDate (
    private val repository: TrackerRepository
){
    suspend operator  fun invoke(
        localDate: LocalDate
    ): Flow<List<TrackedFood>> {
        return repository.getsFoodForDate(localDate)
    }
}