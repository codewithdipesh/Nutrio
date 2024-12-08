package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DailyPercentageCard(
    modifier: Modifier = Modifier,
    name :String,
    amount : Double,
    width:Dp,
    total : Double,
    color: Color
){
    val percentage = (Math.round((amount/total) * 100f * 10) / 10f).toFloat()

    Column(modifier = Modifier
        .width(width)
        .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
      Text(
          text = name,
          color = Color.DarkGray,
          style = MaterialTheme.typography.displayMedium
      )
      Box(
            modifier = modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(percentage / 100f)
                    .background(color)
            )
      }

      Row (
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
      ){
          Text(
              text = "$percentage%",
              color = Color.Black,
              style = MaterialTheme.typography.titleSmall
          )
          Text(
              text = "$total",
              color = Color.Black,
              style = MaterialTheme.typography.titleSmall
          )
      }

    }


}