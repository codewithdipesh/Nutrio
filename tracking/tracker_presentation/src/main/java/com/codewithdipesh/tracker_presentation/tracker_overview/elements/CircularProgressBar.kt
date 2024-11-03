package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core_ui.components.AutoResizeText
import kotlin.math.abs
import kotlin.math.min

@Composable
fun CircularProgressBar(
    currentAmount : Float,
    totalAmount : Float,
    modifier: Modifier =Modifier,
    progressColor : Color = colorResource(R.color.progress_color),
    overflowColorBrush: Brush = Brush.linearGradient(
        colors = listOf(
            colorResource(R.color.overflow_color_1),
            colorResource(R.color.overflow_color_1),
            colorResource(R.color.overflow_color_2),
            colorResource(R.color.overflow_color_2)
        ),
        start = Offset(0f,100f),
        end = Offset(100f,0f),
        tileMode = TileMode.Repeated
    ),
    backgroundColor :Color = colorResource(R.color.progress_background),
    strokeWidth : Float = 20f,
    size : Dp = 200.dp,
    valueTextStyle: TextStyle,
    valueTextColor: Color,
    showIndication : Boolean = false,
    indicationTextStyle: TextStyle,
    indicationtTextColor: Color,

) {
    val spacing = LocalSpacing.current
    val progress = (currentAmount/totalAmount).coerceAtLeast(0f)

    var animatedProgress by remember { mutableStateOf(0f) }


    LaunchedEffect(progress) {
        animate(
            initialValue = 0f,
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 1500,
                easing = FastOutSlowInEasing
            )
        ){value,_ ->
            animatedProgress = value
        }
    }
    Box(
        modifier = modifier.size(size)
            .padding(spacing.spaceSmall),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.matchParentSize()
        ) {
            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

            //progress
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = min(animatedProgress,1f)*360f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

            //overflow
            if(animatedProgress > 1f){
                drawArc(
                    brush = overflowColorBrush,
                    startAngle = -90f,
                    sweepAngle =
                    if(animatedProgress > 2f) 360f
                    else (animatedProgress-1f)*360f,
                    useCenter = false,
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
                )
            }
        }

        Column(
            modifier = Modifier.width(size * 0.8f)
                .fillMaxHeight() ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            AutoResizeText(
                text = "${abs(totalAmount.toInt() -currentAmount.toInt())}",
                color = valueTextColor,
                style = valueTextStyle
            )
            if(showIndication){
                AutoResizeText(
                    text = if(animatedProgress > 1f) "Over" else "Remaining",
                    color = indicationtTextColor,
                    style = indicationTextStyle
                )
            }


        }
    }


}