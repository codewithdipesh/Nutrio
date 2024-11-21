package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    food : TrackedFood,
    viewModel: AddEditViewModel = hiltViewModel(),
    onBackNavigate : () -> Unit = {},
    onDone : () -> Unit = {}
) {
    DefaultScreen(
        foodName = food.name,
        date = LocalDate.now(),
        title = "Edit Food",
        onBackNavigate = onBackNavigate,
        onDone = onDone,
        onEvent = {
            viewModel.onEvent(it)
        }
    )
}