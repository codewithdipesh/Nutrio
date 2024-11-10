package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun NutrientLabel(
    nutrientName: String,
    color: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
   val spacing = LocalSpacing.current
   Row(modifier = modifier
       .wrapContentSize(),
       horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
       verticalAlignment = Alignment.CenterVertically
   ){
     Box(modifier=Modifier
         .size(spacing.spaceMedium)
         .clip(CircleShape)
         .background(color,CircleShape)
     )
     Text(
         text = nutrientName,
         color = textColor,
         style = MaterialTheme.typography.displayMedium
     )
   }
}