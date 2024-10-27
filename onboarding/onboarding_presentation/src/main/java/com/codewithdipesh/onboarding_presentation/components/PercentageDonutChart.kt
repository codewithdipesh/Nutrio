package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
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
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PercentageDonutChart(
    firstPercentage: Float,
    secondPercentage: Float,
    thirdPercentage: Float,
    centerText: String,
    percentageBorderColor: Color = Color.White,
    percentageTextColor: Color = Color.Black,
    chartSize: Dp = 250.dp
) {
    val spacing = LocalSpacing.current
    val total = firstPercentage + secondPercentage + thirdPercentage
    require(total == 100f) { "Percentages must sum to 100" }

    val values = listOf(firstPercentage, secondPercentage, thirdPercentage)
    val colors = listOf(
        Color(0xFFFF4444), // Red
        Color(0xFFFF8C00), // Orange
        Color(0xFF2196F3)  // Blue
    )

    val textMeasurer = rememberTextMeasurer()
    val CalorietextLayoutResult = remember(centerText) {
        textMeasurer.measure(centerText)
    }
    val KCaltextLayoutResult = remember("kcal") {
        textMeasurer.measure("kcal")
    }
    val CalorietextStyle = MaterialTheme.typography.headlineLarge
    val KcaltextStyle = MaterialTheme.typography.labelSmall

    Box(
        modifier = Modifier
            .size(chartSize)
            .padding(spacing.spaceMedium),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
                .aspectRatio(1f)
                .padding(spacing.spaceSmall)
        ) {
            val thickness = size.width / 6f
            val radius = (size.width - thickness) / 2f
            var startAngle = -90f//top

            values.forEachIndexed { index, percentage ->
                val sweepAngle = (percentage / 100f) * 360f
                val middleAngle = startAngle + (sweepAngle / 2f)


                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = thickness, cap = StrokeCap.Butt),
                    size = Size(size.width - thickness, size.height - thickness),
                    topLeft = Offset(thickness / 2f, thickness / 2f)
                )

                // Calculate label position
                val labelRadius = radius + thickness / 2f
                val labelAngleRadians = Math.toRadians(middleAngle.toDouble())
                val labelX = center.x + (labelRadius * cos(labelAngleRadians)).toFloat()
                val labelY = center.y + (labelRadius * sin(labelAngleRadians)).toFloat()

                //shadow of border
                drawRoundRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.2f),
                            Color.Black.copy(alpha = 0.2f),
                            Color.Black.copy(alpha = 0.2f),
                            Color.Black.copy(alpha = 0.05f),
                            Color.Black.copy(alpha = 0.00f)
                        )),
                    topLeft = Offset(labelX - size.width/14f - spacing.spaceSmall.toPx(), labelY - size.height/14f ),
                    size = Size(size.width/14f + spacing.spaceExtraLarge.toPx(),
                        size.height/14f + spacing.spaceMedium.toPx() ),
                    cornerRadius = CornerRadius(spacing.spaceSmall.toPx()),
                    blendMode = BlendMode.SrcOver
                )
                //draw the white border for text
                drawRoundRect(
                    color = percentageBorderColor,
                    topLeft = Offset(labelX - size.width/14f - spacing.spaceSmall.toPx()/2f, labelY - size.height/14f + spacing.spaceSmall.toPx()/2f),
                    size = Size(size.width/14f + spacing.spaceLarge.toPx(),
                        size.height/14f + spacing.spaceSmall.toPx() ),
                    cornerRadius = CornerRadius(spacing.spaceSmall.toPx())
                )

                // Draw percentage label
                drawContext.canvas.nativeCanvas.apply {
                    val labelText = "${percentage.toInt()}%"
                    val textPaint = android.graphics.Paint().apply {
                        textSize = size.width / 14f
                        textAlign = android.graphics.Paint.Align.CENTER
                        color = percentageTextColor.toArgb()
                        isFakeBoldText = true
                    }

                    // Calculate text offset for better positioning
                    val textOffset = textPaint.fontMetrics.let { it.bottom - it.top } / 4

                    drawText(
                        labelText,
                        labelX,
                        labelY + textOffset,
                        textPaint
                    )
                }

                startAngle += sweepAngle
            }

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    text = centerText,
                    textMeasurer = textMeasurer,
                    topLeft = Offset(
                        x = size.width / 2 - CalorietextLayoutResult.size.width.times(1.5f),
                        y = size.height / 2 - CalorietextLayoutResult.size.height.times(1.5f)
                    ),
                    style = CalorietextStyle
                )

                drawText(
                    text = "kcal",
                    textMeasurer = textMeasurer,
                    topLeft = Offset(
                        x = size.width / 2 - KCaltextLayoutResult.size.width,
                        y = size.height / 2 - KCaltextLayoutResult.size.height +
                                CalorietextLayoutResult.size.height +
                                spacing.spaceMedium.toPx()
                    ),
                    style = KcaltextStyle.copy(
                        color = Color.Gray
                    )
                )

            }

        }


    }

}


