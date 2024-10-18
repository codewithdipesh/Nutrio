package com.codewithdipesh.onboarding_presentation.components

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun WeightPicker(
    modifier: Modifier = Modifier,
    style: ScaleStyle = ScaleStyle(),
    minWeight: Float = 20f,
    maxWeight: Float = 150f,
    initialWeight: Float = 70f,
    backgroundColor : Color = Color.White,
    onWeightChange: (Float) -> Unit
) {

    var currentWeight by remember { mutableStateOf(initialWeight) }
    val context = LocalContext.current

    val density = LocalDensity.current
    val lineSpacing = with(density) { 8.dp.toPx() }

    // Calculate initial scroll position to center the initial weight
    val initialScrollPosition = ((initialWeight - minWeight ) * 10 * lineSpacing ).toInt() //2.5 as it taking start as the indicator
    val scrollState = rememberScrollState(initialScrollPosition)

    // Calculate total scroll range
    val totalScrollRange = ((maxWeight - minWeight) * 10 * lineSpacing).toInt()


    val scaleWidth = with(density){totalScrollRange.toDp()}
    var componentWidth by remember { mutableStateOf(0) }



    LaunchedEffect(scrollState.value,componentWidth) {
        if (componentWidth > 0) {
            val centerOffset = scrollState.value + componentWidth / 2 //scrollstate value is the left corner value + half of the screen
            val newWeight = minWeight + (centerOffset / (lineSpacing * 10))  //
            if (newWeight != currentWeight) {
                currentWeight = newWeight.coerceIn(minWeight, maxWeight)
                onWeightChange(currentWeight)
            }
        }
    }

    Box(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                componentWidth = coordinates.size.width
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScroll(
                    state = scrollState,
                    enabled = true
                )
                .width(scaleWidth)
                .align(Alignment.BottomCenter)
        ) {

            val canvasHeight = size.height

            for (i in 0..((maxWeight - minWeight) * 10).toInt()) {
                val x = 0 + i * lineSpacing
                val weight = minWeight + 0.1f * i  //every line => 0.1 kg
                val decimalPart = weight - weight.toInt()
                val lineHeight = when {
                    decimalPart == 0.0f -> style.tenStepLength
                    decimalPart == 0.5f -> style.fiveStepLength
                    else -> style.normalLineLength
                }
                drawLine(
                    color = style.lineColor,
                    start = Offset(x, canvasHeight - lineHeight.toPx() - style.textSize.toPx() - 10.dp.toPx()),
                    end = Offset(x, canvasHeight - style.textSize.toPx() - 10.dp.toPx()),
                    strokeWidth = 2.dp.toPx()
                )

                if (decimalPart == 0.0f) {
                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            weight.toInt().toString(),
                            x,
                            canvasHeight,
                            Paint().apply {
                                color = style.textColor.toArgb()
                                textSize = style.textSize.toPx()
                                textAlign = Paint.Align.CENTER
                                typeface = Typeface.DEFAULT_BOLD
                            }
                        )
                    }
                }
            }
        }


        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .width(totalScrollRange.dp)
        ) {
            //indicator
            drawLine(
                color = Color(context.getColor(R.color.indicator_color)),
                start = Offset(size.width /2, size.height - style.scaleIndicatorLength.toPx() - style.textSize.toPx() - 10.dp.toPx()),
                end = Offset(size.width /2, size.height - style.textSize.toPx() - 10.dp.toPx()),
                strokeWidth =  2.dp.toPx()
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){  //the blurry effect on both edges
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        backgroundColor.copy(1f),
                        backgroundColor.copy(0.5f),
                        Color.Transparent,
                        Color.Transparent,
                        backgroundColor.copy(0.5f),
                        backgroundColor.copy(1f)
                    ),
                    startX = 0f,
                    endX = size.width
                )
            )
        }


    }


}
