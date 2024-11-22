package com.codewithdipesh.tracker_presentation.tracker_overview.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_domain.model.MealType

@Composable
fun MealSelectionBox(
    modifier: Modifier = Modifier,
    onDismiss : ()-> Unit,
    alignment: Alignment = Alignment.TopCenter,
    onSelection : (MealType)->Unit,
) {
    val spacing = LocalSpacing.current
    Box(
        Modifier.fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.1f)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onDismiss()
            }
            .then(modifier),
        contentAlignment = alignment
    ){
        Box(
            modifier = Modifier
                .width(200.dp)
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    //do nothing means box will be opened
                }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                arrayOf(
                    MealType.Breakfast,
                    MealType.Lunch,
                    MealType.Dinner,
                    MealType.Snack).forEach { meal ->
                    Box(Modifier.wrapContentSize()
                        .clickable {
                            onSelection(meal)
                            onDismiss()
                        }
                    ){
                        Text(
                            text = meal.name,
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceMedium)
                        )
                        HorizontalDivider(
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}