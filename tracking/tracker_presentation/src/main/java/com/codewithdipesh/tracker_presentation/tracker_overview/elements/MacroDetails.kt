package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MacroDetails(
    percentage : Float ,
    text: String,
    amount:String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = percentage.toString() + "%",
            color = color,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = amount,
            color = Color.Black,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = text,
            color = Color.Gray,
            style = MaterialTheme.typography.displayMedium
        )
    }
}