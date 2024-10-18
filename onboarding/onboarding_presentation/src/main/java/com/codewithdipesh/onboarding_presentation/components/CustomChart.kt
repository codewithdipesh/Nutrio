package com.codewithdipesh.onboarding_presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.codewithdipesh.core.R
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.common.Fill
import com.patrykandpatrick.vico.core.common.shader.DynamicShader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.min


@Composable
fun CustomChart(
    currentWeight: Float,
    targetWeight: Float,
    targetDate : Date,
    modifier: Modifier = Modifier
){
    val minWeight = min(currentWeight,targetWeight)
    val startYValue = (currentWeight - minWeight).toInt()
    val endYValue = (targetWeight - minWeight).toInt()

    val upwards :Boolean
    if(startYValue < endYValue ){
        upwards = true
    }else{
        upwards = false
    }

    Column (modifier = modifier,
        verticalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp),
            horizontalArrangement = if(upwards) {
                Arrangement.End} else {
                Arrangement.Start}
        ) {
            if(!upwards) Text("$currentWeight kg", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            if(upwards) Text("$targetWeight kg", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.End)
        }

        Spacer(modifier = Modifier.height(4.dp))

        LineChart(
            startYValue = startYValue,
            endYValue = endYValue,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp),
            horizontalArrangement = if(upwards) {
                Arrangement.Start} else {
                Arrangement.End}
        ) {
            if(upwards) Text("$currentWeight kg", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            if(!upwards) Text("$targetWeight kg", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Today", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(SimpleDateFormat("MMM dd", Locale.getDefault()).format(targetDate), fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }


}


@SuppressLint("ResourceAsColor")
@Composable
fun LineChart(
    startYValue: Int,
    endYValue: Int,
    modifier: Modifier = Modifier
) {
    val modelProducer = remember { CartesianChartModelProducer() }

    val context = LocalContext.current
    val color1 = ContextCompat.getColor(context, R.color.chart_color_start)
    val color2 = ContextCompat.getColor(context, R.color.chart_color_middle)
    val color3 = ContextCompat.getColor(context, R.color.chart_color_end)
    val bgColor = ContextCompat.getColor(context, R.color.bg_color)


    val gradientColor = DynamicShader.horizontalGradient(
        colors = intArrayOf(
            color1,
            color2,
            color3
        )
    )

    val areaColor = DynamicShader.verticalGradient(
        colors = intArrayOf(
            bgColor,
            Color.White.toArgb(),
            Color.White.toArgb(),
        ),
        positions = floatArrayOf(
            0.3f,
            1f,
            0.7f
        )
    )
    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineSeries { series(startYValue,endYValue) }
        }
    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lineProvider = LineCartesianLayer.LineProvider.series(
                    listOf(
                        LineCartesianLayer.rememberLine(
                            fill = LineCartesianLayer.LineFill.single(Fill(shader = gradientColor)),
                            thickness = 8.dp,
                            areaFill = LineCartesianLayer.AreaFill.single(Fill(shader = areaColor))
                        )
                    )
                ),
                pointSpacing = 24.dp
            )
        ),

        modelProducer = modelProducer,
        modifier = modifier
            .fillMaxWidth()

    )



}

