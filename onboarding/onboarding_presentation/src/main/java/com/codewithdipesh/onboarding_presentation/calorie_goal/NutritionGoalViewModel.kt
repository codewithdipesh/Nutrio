package com.codewithdipesh.onboarding_presentation.calorie_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.CalorieCounter
import com.codewithdipesh.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionGoalViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

   var calorieGoal by mutableStateOf(preferences.loadUserInfo().calorieGoal)
       private set

   val _UiEvent = Channel<UiEvent>()
   val uiEvent = _UiEvent.receiveAsFlow()

    fun calculateCalorie(){
        val userInfo = preferences.loadUserInfo()
        val calorie = CalorieCounter(
            userInfo.weight,
            userInfo.height,
            userInfo.age,
            userInfo.gender,
            userInfo.activityLevel,
            userInfo.goalType,
            userInfo.weightPace
        )
        calorieGoal = calorie

    }

   fun onNextClick(){
        viewModelScope.launch {
            preferences.saveCalorieGoal(calorieGoal)
            preferences.saveCarbRatio(50f)
            preferences.saveProteinRatio(20f)
            preferences.saveFatRatio(30f)
            _UiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
        }

    }
}