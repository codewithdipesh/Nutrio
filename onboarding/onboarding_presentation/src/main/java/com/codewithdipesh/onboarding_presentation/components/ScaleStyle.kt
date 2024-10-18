package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R

data class ScaleStyle(
    val scaleWidth : Dp = 60.dp,
    val scaleLength:Dp = 300.dp,
    val lineColor : Color = Color.LightGray,
    val normalLineLength:Dp = 20.dp,
    val fiveStepLength:Dp = 30.dp,
    val tenStepLength:Dp = 40.dp,
    val scaleIndicatorColor: Color = Color(R.color.indicator_color.toInt()),
    val scaleIndicatorLength:Dp = 60.dp,
    val textSize:Dp = 14.dp,
    val textColor: Color = Color(R.color.scale_text_color.toInt())
)
