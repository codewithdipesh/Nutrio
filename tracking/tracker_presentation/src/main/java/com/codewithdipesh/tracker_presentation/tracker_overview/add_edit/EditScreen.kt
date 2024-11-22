package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditState
import java.time.LocalDate

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    state: AddEditState,
    isMealBoxOpen : Boolean ,
    isSizeBoxOpen : Boolean ,
    onBackNavigate : () -> Unit,
    onDone : () -> Unit ,
    onEvent : (AddEditEvent) -> Unit ,
    snackbarHostState : SnackbarHostState
) {

    DefaultScreen(
        foodName = state.food.name,
        date = LocalDate.now(),
        title = "Edit Food",
        onBackNavigate = onBackNavigate,
        onDone = onDone,
        onEvent = {
           onEvent(it)
        },
        isMealBoxOpen = isMealBoxOpen,
        isSizeBoxOpen = isSizeBoxOpen,
        snackbarHostState = snackbarHostState,
        mealType = state.mealType,
        NumberOfServings = state.amount,
        ServingSize = state.unit,
    )
}