package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.model.Meal
import com.codewithdipesh.tracker_presentation.tracker_overview.search_food.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@Composable
fun  AddEditScreen(
    viewModel: AddEditViewModel = hiltViewModel(),
    id: Int?,
    food: TrackableFood? = null,
    mealType: MealType = MealType.Breakfast,
    onBackNavigate: () -> Unit,
    onNavigate :(UiEvent.Navigate)->Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        if (id != -1) {
            viewModel.initFood(id!!)
        }else{
            if(food!=null){
                viewModel.initFood(
                    food,
                    mealType
                )
            }else{
                Log.d("AddEditScreen", "AddEditScreen: Food is null")
                onBackNavigate()
            }
        }
    }
    LaunchedEffect(true) {
       viewModel.uiEvent.collect{
           when(it){
               UiEvent.NavigateUp -> onBackNavigate()
               is UiEvent.Navigate -> onNavigate(it)
               is UiEvent.showSnackBar -> {
                   snackbarHostState.showSnackbar(
                       message = it.msg
                   )
               }
               is UiEvent.SuccessAndNavigate ->{
                   withContext(Dispatchers.Main) {
                       val job = launch {
                           snackbarHostState.showSnackbar(
                               message = it.msg,
                               duration = SnackbarDuration.Short
                           )
                       }
                       delay(1000)
                       job.cancel()
                       onBackNavigate()
                   }
               }
               else -> {}
           }
       }
    }
    DefaultScreen(
            foodName = state.food.name,
            date = LocalDate.now(),
            title = if(id == -1) "Add Food" else "Edit Food",
            onBackNavigate = onBackNavigate,
            onDone = {
                viewModel.onEvent(AddEditEvent.OnSave(state.food))
            },
            onEvent = {
                viewModel.onEvent(it)
            },
            isMealBoxOpen = state.isMealExpanded,
            isSizeBoxOpen = state.isSizeUnitExpanded,
            snackbarHostState = snackbarHostState,
            mealType = state.mealType,
            NumberOfServings = state.amount,
            ServingSize = state.unit,
            carbs = state.carb,
            protein = state.protein,
            fat = state.fat,
            calories = state.calories.toDouble()

    )


}