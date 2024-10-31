package com.codewithdipesh.tracker_domain.model

sealed class Unit(val name : String){
    object TableSpoon : Unit("tablespoon")
    object TeaSpoon : Unit("teaspoon")
    object Ounce : Unit("ounce")
    object Whole : Unit("whole")
    object Gm100 : Unit("gram")
    object Cup : Unit("cup")

    companion object{
        fun fromString(name : String):Unit{
            return when(name){
                "tablespoon" -> TableSpoon
                "teaspoon" -> TeaSpoon
                "ounce" -> Ounce
                "whole" -> Whole
                "gram" -> Gm100
                "cup" -> Cup
                else -> Gm100
            }
        }
    }
}