package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditEvent

@Composable
fun  AddEditScreen(
    viewModel: AddEditViewModel = hiltViewModel(),
    id: Int?,
    onBackNavigate: () -> Unit ,
    onNavigate :(UiEvent.Navigate)->Unit ,
    modifier: Modifier = Modifier,
) {

    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
       viewModel.initFood(id ?: -1)
    }
    LaunchedEffect(true) {
       viewModel.uiEvent.collect{
           when(it){
               UiEvent.NavigateUp -> onBackNavigate()
               is UiEvent.Navigate -> onNavigate(it)
               else -> {}
           }
       }
    }

    if(id != 0){
        EditScreen(
            food = state.food,
            onDone = {
               viewModel.onEvent(AddEditEvent.OnSave(state.food))
            },
            onBackNavigate = {
                onBackNavigate()
            }
        )
    }

}