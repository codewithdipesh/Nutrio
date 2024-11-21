package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val trackerUsecases : TrackerUseCases
): ViewModel() {
    private var _state = MutableStateFlow<TrackedFood>(
        TrackedFood(
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
        id = -1)
    )
    val state = _state.asStateFlow()

    fun refresh(id : Int){
        viewModelScope.launch {
           try {
               val food = trackerUsecases.getFoodById(id)
                   ?: throw NoSuchElementException("Food not found")
               food.collect {
                   _state.value = it
               }

           }catch (e:Exception){
               Log.d("AddEditViewModel", "refresh: ${e.message}")
           }
        }


    }
}