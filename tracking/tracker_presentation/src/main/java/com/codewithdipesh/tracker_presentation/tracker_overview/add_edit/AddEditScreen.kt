package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(Unit) {
       viewModel.refresh(id ?: -1)
    }
    if(id != 0){
        EditScreen(
           food = viewModel.state
        )
    }

}