package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R

@Composable
fun ProgressBar(
    width: Dp,
    stroke: Dp = 8.dp,
    selectedColor: Color = colorResource(R.color.indicator_color),
    unselectedColor: Color = Color.LightGray,
    totalProgress: Int,
    spacing: Dp = 4.dp,
    currentProgress: Int,
    modifier: Modifier = Modifier,
) {
    val roundedShape = RoundedCornerShape(20.dp)
    Surface(
        modifier = modifier
            .height(stroke)
            .width(width)
            .clip(roundedShape),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            repeat(totalProgress) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            color = if(index < currentProgress) selectedColor else unselectedColor,
                            shape = roundedShape
                        )
                )
            }
        }
    }
}