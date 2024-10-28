package com.codewithdipesh.core.domain.model

sealed class ActivityLevel(val name : String){
    object Sedentary : ActivityLevel("sedentary")
    object Light : ActivityLevel("light")
    object Moderate : ActivityLevel("moderate")
    object Active : ActivityLevel("active")
    object VeryActive : ActivityLevel("very_active")

    companion object{
        fun fromString(name : String) : ActivityLevel{
            return when(name){
                "sedentary" -> Sedentary
                "light" -> Light
                "moderate" -> Moderate
                "active" -> Active
                "very_active" -> VeryActive
                else -> Moderate
            }
        }
    }
}
