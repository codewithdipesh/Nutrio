package com.codewithdipesh.core.domain.model

sealed class WeightPace(val name : String){
    object Moderate : WeightPace("moderate")
    object Extreme : WeightPace("extreme")

    companion object{
        fun fromString(name : String) : WeightPace{
            return when(name){
                "moderate" -> Moderate
                "extreme" -> Extreme
                else -> Extreme
            }
        }
    }
}
