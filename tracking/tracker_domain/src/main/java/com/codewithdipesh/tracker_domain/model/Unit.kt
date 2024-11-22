package com.codewithdipesh.tracker_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Unit(val name : String):Parcelable{
    @Parcelize
    object TableSpoon : Unit("tablespoon")
    @Parcelize
    object TeaSpoon : Unit("teaspoon")
    @Parcelize
    object Ounce : Unit("ounce")
    @Parcelize
    object Whole : Unit("whole")
    @Parcelize
    object Gm100 : Unit("gram")
    @Parcelize
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