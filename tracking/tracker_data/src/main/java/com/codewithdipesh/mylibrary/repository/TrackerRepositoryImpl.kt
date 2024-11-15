package com.codewithdipesh.mylibrary.repository

import android.util.Log
import com.codewithdipesh.mylibrary.local.TrackerDao
import com.codewithdipesh.mylibrary.mappers.toFoodName
import com.codewithdipesh.mylibrary.mappers.toTrackedFood
import com.codewithdipesh.mylibrary.mappers.toTrackedFoodEntity
import com.codewithdipesh.mylibrary.mappers.toUnitNutrition
import com.codewithdipesh.mylibrary.remote.OpenFoodApi
import com.codewithdipesh.mylibrary.remote.dto.SearchRequest
import com.codewithdipesh.mylibrary.remote.getSearchText
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFood(
       food : String
    ): Result<TrackableFood> {
        return try {
            val searchText = getSearchText(food)
            val searchDto = api.searchFood(
               request = SearchRequest(
                   ingr = searchText
               )
            )
            Log.d("SEARCH_RESULT",searchDto.toString())
            val foodName = searchDto.toFoodName() ?: return Result.failure(IllegalArgumentException("Food name not found"))
            val nutrients = searchDto.ingredients?.toUnitNutrition() ?: emptyMap()
            Result.success(
                TrackableFood(
                    name = foodName,
                    nutrients = nutrients
                )
            )
        }catch(e:HttpException){
            e.printStackTrace()
            Result.failure(
                if (e.code() == 555) IllegalArgumentException("Food not found") else IllegalArgumentException("Unknown Error")
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