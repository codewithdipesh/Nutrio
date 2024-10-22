package com.codewithdipesh.core.domain.Preferences

import com.codewithdipesh.core.domain.model.ActivityLevel
import com.codewithdipesh.core.domain.model.Gender
import com.codewithdipesh.core.domain.model.GoalType
import com.codewithdipesh.core.domain.model.UserInfo
import com.codewithdipesh.core.domain.model.WeightPace

interface Preferences {
    fun saveGender(gender : Gender)
    fun saveAge(age : Int)
    fun saveHeight(height : Float)
    fun saveWeight(weight : Float)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(type : GoalType)
    fun saveWeightPace(pace:WeightPace)
    fun saveCarbRatio(ratio : Float)
    fun saveProteinRatio(ratio : Float)
    fun saveFatRatio(ratio : Float)


    fun loadUserInfo(): UserInfo

    companion object{
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_HEIGHT = "height"
        const val KEY_WEIGHT = "weight"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_WEIGHT_PACE = "weight_pace"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}