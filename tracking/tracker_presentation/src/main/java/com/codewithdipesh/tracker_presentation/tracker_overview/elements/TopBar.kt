package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.getStringFromDate
import com.codewithdipesh.core_ui.LocalSpacing
import java.time.LocalDate
import java.util.Locale

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    onDateClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    //TopBar
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = spacing.spaceLarge)
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = spacing.default),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier.weight(1f)){
            Text(
                text = getStringFromDate(selectedDate),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                modifier = Modifier.clickable {
                    onDateClick()
                }
            )
        }

        Text(
            text = stringResource(R.string.app_name).uppercase(Locale.ENGLISH),
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.progress_color),
            modifier = Modifier.weight(1f)
        )
        //TODO ADD A SETTINGS OPTION
        Text(
            text = stringResource(R.string.today),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Transparent,
            modifier = Modifier.weight(1f)
        )


    }
}