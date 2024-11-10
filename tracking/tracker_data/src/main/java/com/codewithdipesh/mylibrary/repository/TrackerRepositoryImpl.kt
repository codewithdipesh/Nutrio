package com.codewithdipesh.mylibrary.repository

import com.codewithdipesh.mylibrary.local.TrackerDao
import com.codewithdipesh.mylibrary.mappers.toFoodName
import com.codewithdipesh.mylibrary.mappers.toTrackedFood
import com.codewithdipesh.mylibrary.mappers.toTrackedFoodEntity
import com.codewithdipesh.mylibrary.mappers.toUnitNutrition
import com.codewithdipesh.mylibrary.remote.OpenFoodApi
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFood(
       food : String
    ): Result<TrackableFood> {
        return try {
            val searchText = api.getSearchText(food)
            val searchDto = api.searchFood(
               ingr = searchText
            )
            Result.success(
                TrackableFood(
                    name = searchDto.toFoodName(),
                    nutrients = searchDto.ingredients.toUnitNutrition()
                )
            )
        }catch(e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getsFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}