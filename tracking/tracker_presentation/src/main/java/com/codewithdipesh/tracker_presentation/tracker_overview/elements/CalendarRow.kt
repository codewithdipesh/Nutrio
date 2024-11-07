package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import java.time.LocalDate

@Composable
fun CalendarRow(
    listofShownDates : List<LocalDate>,
    onSelect : (LocalDate)-> Unit,
    selectedDate : LocalDate = LocalDate.now(),
    onNextWeek : ()-> Unit,
    onPreviousWeek : ()-> Unit,
) {
    val dateList by remember(listofShownDates) {
        mutableStateOf(listofShownDates)
    }
    val density = LocalDensity.current

    val spacing = LocalSpacing.current
    var dateItemWidth by remember{
        mutableStateOf(20.dp)
    }
    Row (
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = spacing.spaceMedium)
            .onGloballyPositioned {
                val width = with(density){ it.size.width.toDp()}
                dateItemWidth = width * 0.114f
            }
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
       IconButton(onClick = {
           onPreviousWeek()
            },
           modifier = Modifier.weight(0.1f)
       ) {
           Icon(
               imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
               contentDescription = null,
               tint = colorResource(R.color.medium_gray)
           )
       }
        dateList.forEach { date->
            DateItem(
                date = date,
                isSelected = selectedDate == date,
                onClick = {
                    onSelect(date)
                },
                modifier = Modifier.width(dateItemWidth)
                    .weight(0.114f)
            )
        }

        IconButton(onClick = {
            onNextWeek()
        },
            modifier = Modifier.weight(0.1f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = colorResource(R.color.medium_gray)
            )
        }

    }
}