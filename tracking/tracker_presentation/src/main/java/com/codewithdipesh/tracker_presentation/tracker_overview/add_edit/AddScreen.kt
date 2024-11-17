package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codewithdipesh.tracker_domain.model.TrackableFood
import java.time.LocalDate

@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    food : TrackableFood,
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