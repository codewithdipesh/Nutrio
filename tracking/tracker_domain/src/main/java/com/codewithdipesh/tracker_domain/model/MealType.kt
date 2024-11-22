package com.codewithdipesh.tracker_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed  class MealType(val name : String):Parcelable{
    @Parcelize
    object Breakfast : MealType("Breakfast")
    @Parcelize
    object Lunch : MealType("Lunch")
    @Parcelize
    object Dinner : MealType("Dinner")
    @Parcelize
    object Snack : MealType("Snack")

    companion object{
        fun fromString(name : String):MealType{
         return when(name){
             "Breakfast" -> Breakfast
             "Lunch" -> Lunch
             "Dinner" -> Dinner
             "Snack" -> Snack
             else -> Breakfast
         }
        }
    }
}