package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateItem(
    date: LocalDate,
    normalBgColor : Color = Color.Transparent,
    selectedBgColor : Color = colorResource(R.color.light_gray),
    textColor: Color = colorResource(R.color.dark_gray),
    selectedTextColor : Color = colorResource(R.color.progress_color),
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val selected by remember(isSelected) {
        mutableStateOf(isSelected)
    }
    Column(
        modifier = modifier
            .wrapContentHeight()
            .background(
                if(selected) selectedBgColor
                else normalBgColor
            )
            .then(
                if(date == LocalDate.now()) Modifier.background(
                    color = colorResource(R.color.progress_color)
                )
                else Modifier
            )
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(spacing.spaceExtraSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 16.sp
            ),
            color = if (selected) selectedTextColor
                else textColor,
        )
        Text(
            text = (date.format(DateTimeFormatter.ofPattern("E")))[0].toString(), //M from Monday
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 16.sp
            ),
            color = textColor,
        )
    }
}