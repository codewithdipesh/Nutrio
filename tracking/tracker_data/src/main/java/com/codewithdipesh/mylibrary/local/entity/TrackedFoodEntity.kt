package com.codewithdipesh.mylibrary.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackedFoodEntity(
    val name : String,
    val carbs :Double,
    val protein :Double,
    val fat :Double,
    val type:String,
    val unit :String,
    val amount:Int,
    val dayOfMonth:Int,
    val month:Int,
    val year:Int,
    val calories:Int,
    val fibers:Double,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null

)