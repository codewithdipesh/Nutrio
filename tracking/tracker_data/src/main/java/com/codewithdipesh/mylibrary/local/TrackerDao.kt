package com.codewithdipesh.mylibrary.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewithdipesh.mylibrary.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackFood(trackedFoodEntity : TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity : TrackedFoodEntity)

    @Query(
        """
            SELECT *
            FROM trackedfoodentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getFoodsForDate(day : Int,month:Int,year:Int): Flow<List<TrackedFoodEntity>>

}