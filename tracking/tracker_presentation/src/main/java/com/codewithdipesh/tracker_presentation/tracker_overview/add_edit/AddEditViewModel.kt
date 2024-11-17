package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val trackerUsecases : TrackerUseCases
): ViewModel() {
    var state by mutableStateOf(TrackedFood(
        name = "",
        _carbs = 0.0,
        _protein = 0.0,
        _fat = 0.0,
        amount = 0,
        _fiber = 0.0,
        unit = Unit.Gm100,
        calories = 0,
        mealType = MealType.Breakfast,
        date = LocalDate.now(),
        nutrients = emptyMap(),
        id = -1,
    ))

    fun refresh(id : Int){
        viewModelScope.launch {
            val food = trackerUsecases.getFoodById(id)
            if(food == null) throw IllegalArgumentException("Food not found")
            food?.let {
               it.onEach {
                   state = it
               }
            }
        }


    }
}