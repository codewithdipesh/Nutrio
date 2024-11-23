package com.codewithdipesh.tracker_domain.model

import kotlinx.serialization.Serializable


@Serializable
sealed  class MealType(val name : String){
    @Serializable
    object Breakfast : MealType("Breakfast")
    @Serializable
    object Lunch : MealType("Lunch")
    @Serializable
    object Dinner : MealType("Dinner")
    @Serializable
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