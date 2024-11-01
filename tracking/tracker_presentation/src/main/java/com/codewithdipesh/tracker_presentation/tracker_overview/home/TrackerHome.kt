package com.codewithdipesh.tracker_presentation.tracker_overview.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CircularProgressBar

@Composable
fun TrackerHome(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressBar(
            currentAmount = 800.toFloat(),
            totalAmount = 1000.toFloat(),
            valueTextStyle = MaterialTheme.typography.titleMedium,
            valueTextColor = Color.Black,
            indicationTextStyle = MaterialTheme.typography.labelMedium,
            indicationtTextColor = Color.DarkGray,
        )
    }

}