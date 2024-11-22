package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.Nutrients
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditState
import com.codewithdipesh.tracker_presentation.tracker_overview.search_food.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val trackerUsecases : TrackerUseCases
): ViewModel() {
    private var _state = MutableStateFlow(AddEditState())
    val state = _state.asStateFlow()

    val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvent){
        when(event){
            AddEditEvent.OnBackNavigate -> {
                _uiEvent.trySend(
                    UiEvent.NavigateUp
                )
            }
            is AddEditEvent.OnClickMeal -> {
                _state.value = _state.value.copy(
                   mealType = event.mealType
                )
                refresh()
            }
            is AddEditEvent.OnClickSize -> {
                _state.value = _state.value.copy(
                    amount = event.size
                )
                refresh()
            }
            is AddEditEvent.OnSave -> {
                viewModelScope.launch {
                    trackerUsecases.trackFood(
                        food = event.food,
                        unit = _state.value.unit,
                        amount = _state.value.amount,
                        mealType = _state.value.mealType,
                        date = _state.value.date
                    )
                    _uiEvent.send(
                        UiEvent.SuccessAndNavigate("Food Added Successfully")
                    )
                }

            }
            is AddEditEvent.OnSelectUnit -> {
                _state.value = _state.value.copy(
                    unit = event.unit
                )
                refresh()
            }
            AddEditEvent.OnToggleMeal -> {
                _state.value = _state.value.copy(
                    isSizeExpanded = false,
                    isUnitExpanded = false,
                    isMealExpanded = !_state.value.isMealExpanded
                )
            }
            AddEditEvent.OnToggleSize -> {
                _state.value = _state.value.copy(
                    isUnitExpanded = false,
                    isMealExpanded = false,
                    isSizeExpanded = !_state.value.isSizeExpanded
                )
            }
            AddEditEvent.OnToggleUnit -> {
                _state.value = _state.value.copy(
                    isSizeExpanded = false,
                    isMealExpanded = false,
                    isUnitExpanded = !_state.value.isUnitExpanded
                )

            }
        }

    }

    fun refresh(){
       val meal = _state.value.mealType
       val unit = _state.value.unit
       val amount = _state.value.amount
       val nutrients = getNutrients(unit, amount)
       _state.value = _state.value.copy(
           unit =unit,
           amount = amount,
           mealType = meal,
           calories = nutrients.calories.toInt(),
           protein = nutrients.protein,
           fat = nutrients.fat,
           carb = nutrients.carbs,
           fiber = nutrients.fiber
       )
    }
    fun initFood(
        trackableFood: TrackableFood,
        mealType: MealType
    ){
        viewModelScope.launch {
            val food = trackerUsecases.getTrackedFood(
                food = trackableFood,
                mealType = mealType
            )
            _state.value = _state.value.copy(
                food =food,
                carb = food.carbs,
                protein = food.protein,
                fat = food.fat,
                amount = food.amount,
                fiber = food.fiber,
                unit = food.unit,
                calories = food.calories,
                mealType = food.mealType,
                date = food.date,
                id = food.id ?: -1
            )
        }
    }
    fun initFood(id : Int){
        viewModelScope.launch {
           try {
               val food = trackerUsecases.getFoodById(id)
                   ?: throw NoSuchElementException("Food not found")
               food.collect {
                   _state.value = _state.value.copy(
                       food =it,
                       carb = it.carbs,
                       protein = it.protein,
                       fat = it.fat,
                       amount = it.amount,
                       fiber = it.fiber,
                       unit = it.unit,
                       calories = it.calories,
                       mealType = it.mealType,
                       date = it.date,
                       id = it.id ?: -1
                   )
               }

           }catch (e:Exception){
               _uiEvent.trySend(
                   UiEvent.showSnackBar(
                       msg = e.message ?: "Food not found"
                   )
               )
           }
        }
    }

    private fun getNutrients(unit: Unit, amount: Double): Nutrients {
        return Nutrients(
            _calories = ((_state.value.food.nutrients[unit]?.calories)?.times(amount)) ?: 0.0,
            _carbs = ((_state.value.food.nutrients[unit]?.carbs)?.times(amount)) ?: 0.0,
            _protein = ((_state.value.food.nutrients[unit]?.protein)?.times(amount)) ?: 0.0,
            _fat = ((_state.value.food.nutrients[unit]?.fat)?.times(amount)) ?: 0.0,
            _fiber = ((_state.value.food.nutrients[unit]?.fiber)?.times(amount)) ?: 0.0,

        )
    }
}