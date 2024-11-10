package com.codewithdipesh.mylibrary.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithdipesh.mylibrary.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val dao : TrackerDao
}