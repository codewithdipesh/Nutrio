package com.codewithdipesh.tracker_presentation.tracker_overview.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CircularProgressBar

@Composable
fun TrackerHome(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressBar(
            currentAmount = 12000.toFloat(),
            totalAmount = 1000.toFloat(),
            valueTextStyle = MaterialTheme.typography.bodyLarge,
            valueTextColor = Color.Black,
            indicationTextStyle = MaterialTheme.typography.titleSmall,
            indicationtTextColor = Color.DarkGray,
            size = 150.dp,
            progressColor = colorResource(R.color.protein),
            overflowColorBrush = Brush.verticalGradient(
                colors = listOf(
                    colorResource(R.color.protein_overflow_1),
                    colorResource(R.color.protein_overflow_1),
                    colorResource(R.color.protein_overflow_2),
                    colorResource(R.color.protein_overflow_2)
                ),
                startY = 0.0f,
                endY = 20.0f,
                tileMode = TileMode.Repeated
            ),
            strokeWidth = 40f
        )

    }

}