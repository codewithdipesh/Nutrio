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
    val amount:Double,
    val dayOfMonth:Int,
    val month:Int,
    val year:Int,
    val calories:Int,
    val fibers:Double,

    val TableSpoonCalories: Double,
    val TableSpoonCarbs: Double,
    val TableSpoonProtein: Double,
    val TableSpoonFat: Double,
    val TableSpoonFiber: Double,

    val TeaSpoonCalories: Double,
    val TeaSpoonCarbs: Double,
    val TeaSpoonProtein: Double,
    val TeaSpoonFat: Double,
    val TeaSpoonFiber: Double,

    val OunceCalories: Double,
    val OunceCarbs: Double,
    val OunceProtein: Double,
    val OunceFat: Double,
    val OunceFiber: Double,

    val WholeCalories: Double,
    val WholeCarbs: Double,
    val WholeProtein: Double,
    val WholeFat: Double,
    val WholeFiber: Double,

    val Gm100Calories: Double,
    val Gm100Carbs: Double,
    val Gm100Protein: Double,
    val Gm100Fat: Double,
    val Gm100Fiber: Double,

    val CupCalories: Double,
    val CupCarbs: Double,
    val CupProtein: Double,
    val CupFat: Double,
    val CupFiber: Double,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null

)