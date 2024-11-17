package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codewithdipesh.tracker_domain.model.TrackedFood
import java.time.LocalDate

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    food : TrackedFood,
    onBackNavigate : () -> Unit = {},
    onDone : () -> Unit = {}
) {
    DefaultScreen(
        foodName = food.name,
        date = LocalDate.now(),
        title = "Add Food",
        onBackNavigate = onBackNavigate,
        onDone = onDone
    )
}