package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.components.AutoResizeText
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PercentageDonutChart(
    firstPercentage: Float,
    secondPercentage: Float,
    thirdPercentage: Float,
    text: String,
    textColor: Color,
    chartSize: Dp = 80.dp
) {
    val spacing = LocalSpacing.current
    val totalMacros = firstPercentage + secondPercentage + thirdPercentage

    val normalizedFirst = if (totalMacros == 0f) 0f else (firstPercentage / totalMacros) * 100f
    val normalizedSecond = if (totalMacros == 0f) 0f else (secondPercentage / totalMacros) * 100f
    val normalizedThird = if (totalMacros == 0f) 0f else (thirdPercentage / totalMacros) * 100f

    val formattedFirst  = (Math.round(normalizedFirst * 10.0) / 10.0).toFloat()
    val formattedSecond  = (Math.round(normalizedSecond * 10.0) / 10.0).toFloat()
    val formattedThird  = (Math.round(normalizedThird * 10.0) / 10.0).toFloat()

     Log.d("PercentageDonutChart", "PercentageDonutChart: $formattedFirst $formattedSecond $formattedThird")
    val values = listOf(formattedFirst, formattedSecond, formattedThird)
    val colors = listOf(
        colorResource(R.color.carb),
        colorResource(R.color.protein),
        colorResource(R.color.fat)
    )

    val textMeasurer = rememberTextMeasurer()
    val CalorieAmountLayoutResult = remember(text) {
        textMeasurer.measure(text)
    }
    val CaltextLayoutResult = remember() {
        textMeasurer.measure("Cal")
    }
    val CalorieAmountTextResult = MaterialTheme.typography.labelMedium
    val CaltextStyle = MaterialTheme.typography.displayMedium

    val SingleInputBorderthicknessInAngle = 1f //2 degree
    val SingleInputBorderColor = Color.DarkGray

    if(formattedFirst+formattedSecond+formattedThird > 99.9f){
        Box(
            modifier = Modifier
                .size(chartSize),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .aspectRatio(1f)
            ) {
                val thickness = size.width / 11f
                var startAngle = -90f//top

                values.forEachIndexed { index, percentage ->
                    val sweepAngle = (percentage / 100f) * 360f
                    drawArc(
                        color = SingleInputBorderColor,
                        startAngle = startAngle,
                        sweepAngle = SingleInputBorderthicknessInAngle,
                        useCenter = false,
                        style = Stroke(width = thickness, cap = StrokeCap.Butt),
                        size = Size(size.width - thickness, size.height - thickness),
                        topLeft = Offset(thickness / 2f, thickness / 2f)
                    )
                    startAngle += SingleInputBorderthicknessInAngle

                    drawArc(
                        color = colors[index],
                        startAngle = startAngle,
                        sweepAngle = sweepAngle - SingleInputBorderthicknessInAngle,
                        useCenter = false,
                        style = Stroke(width = thickness, cap = StrokeCap.Butt),
                        size = Size(size.width - thickness, size.height - thickness),
                        topLeft = Offset(thickness / 2f, thickness / 2f)
                    )
                    startAngle += sweepAngle - SingleInputBorderthicknessInAngle
                }
            }

            Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AutoResizeText(
                    text = text,
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium
                )
                AutoResizeText(
                    text = "Cal",
                    color = Color.Gray,
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }

    }
}


