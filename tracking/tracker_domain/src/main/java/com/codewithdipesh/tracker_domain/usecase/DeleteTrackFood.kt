package com.codewithdipesh.tracker_domain.usecase

import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlin.math.roundToInt

class DeleteTrackFood (
    private val repository : TrackerRepository
) {

    suspend operator fun invoke(trackedFood : TrackedFood){
        return repository.deleteTrackedFood(trackedFood)
    }

}