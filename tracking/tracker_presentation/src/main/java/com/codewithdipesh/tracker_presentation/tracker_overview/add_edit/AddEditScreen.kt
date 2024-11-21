package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_domain.model.TrackedFood

@Composable
fun  AddEditScreen(
    viewModel: AddEditViewModel = hiltViewModel(),
    id: Int?,
    modifier: Modifier = Modifier,
) {
    Log.d("AddEditScreen", "id: $id")
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
       viewModel.refresh(id ?: -1)
    }
    if(id != 0){
        EditScreen(
           food = state
        )
    }

}