package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun CustomRowWithAction(
    modifier: Modifier = Modifier,
    actionContent : @Composable () -> Unit,
    titleContent : @Composable () -> Unit
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        titleContent()
        actionContent()
    }
}