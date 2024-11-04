package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.Dimensions
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core_ui.components.AutoResizeText
import kotlin.math.abs

@Composable
fun CalorieCard(
    totalCalories : Int = 1200,
    caloriesGoal : Int = 2800,
    caloriesBurned : Int = 600,
) {
    val spacing = LocalSpacing.current

    val calories by remember(totalCalories) {
        mutableStateOf(totalCalories)
    }
    val goalCalories by remember(caloriesGoal) {
        mutableStateOf(caloriesGoal)
    }
    val burnedCalories by remember(caloriesBurned) {
        mutableStateOf(caloriesBurned)
    }

    val netCalorieNeed by remember(goalCalories, burnedCalories, calories) {
        mutableStateOf(goalCalories + burnedCalories - calories)
    }

    OverviewCard(
        title = stringResource(R.string.calories),
        showDescription = true,
        description = "Remaining = Goal - Food + Exercise",
        height = 300.dp
    ){
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = LocalSpacing.current.spaceSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            //calorie progress Bar
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f)
            ) {
                CircularProgressBar(
                    currentAmount = totalCalories.toFloat(),
                    totalAmount = caloriesGoal.toFloat(),
                    burnedAmount = caloriesBurned.toFloat(),
                    burnedColor = colorResource(R.color.burned),
                    valueTextStyle = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 34.sp
                    ),
                    valueTextColor = Color.Black,
                    indicationTextStyle = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 16.sp
                    ),
                    indicationtTextColor = Color.DarkGray,
                    size = 120.dp,
                    progressColor = colorResource(R.color.progress_color),
                    overflowColorBrush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.overflow_color_1),
                            colorResource(R.color.overflow_color_1),
                            colorResource(R.color.overflow_color_2),
                            colorResource(R.color.overflow_color_2)
                        ),
                        startY = 0.0f,
                        endY = 20.0f,
                        tileMode = TileMode.Repeated
                    ),
                    showIndication = true,
                    strokeWidth = 30f,
                    chartPrimaryText = "${abs(netCalorieNeed)}",
                    chartSecondaryText = if(netCalorieNeed >= 0 ) "Remaining" else "Over"
                )
            }
            Spacer(modifier = Modifier.width(spacing.spaceLarge))
            //Deatils
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = spacing.spaceLarge),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(Modifier.height(spacing.spaceSmall))
                AmountWithTitle(
                    title = stringResource(R.string.base_goal),
                    amount = "${caloriesGoal}"
                )
                AmountWithTitle(
                    title = stringResource(R.string.food),
                    amount = "${totalCalories}"
                )
                AmountWithTitle(
                    title = stringResource(R.string.exercise),
                    amount = "${caloriesBurned}"
                )
            }
        }
    }
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
        AutoResizeText(
            text = title,
            color = titleColor,
            style = titleStyle
        )
        AutoResizeText(
            text = amount,
            color = amountColor,
            style = amountStyle
        )

    }
}