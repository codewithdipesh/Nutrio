package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.Dimensions
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun CalorieCard(

) {
    OverviewCard(
        title = stringResource(R.string.calories),
        showDescription = true,
        description = "Remaining = Goal - Food + Exercise",
        height = 270.dp,
        width = LocalConfiguration.current.screenWidthDp.dp - 40.dp
    )
}


@Composable
fun AmountWithTitle(
    title : String,
    amount : String,
    titleColor: Color = Color.DarkGray,
    amountColor: Color = Color.Black,
    titleStyle : TextStyle = MaterialTheme.typography.displayMedium,
    amountStyle : TextStyle = MaterialTheme.typography.displayMedium.copy(
        fontWeight = FontWeight.Bold
    ),
    space : Dimensions = LocalSpacing.current
){
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = titleColor,
            style = titleStyle
        )
        Text(
            text = amount,
            color = amountColor,
            style = amountStyle
        )

    }
}