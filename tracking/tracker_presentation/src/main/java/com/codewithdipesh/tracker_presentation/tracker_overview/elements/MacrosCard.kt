package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import android.graphics.Paint.Align
import android.util.Log
import android.widget.Space
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.Dimensions
import com.codewithdipesh.core_ui.LocalSpacing
import kotlin.math.abs

@Composable
fun MacrosCard(
    proteinDailyGoal : Int = 90,
    fatDailyGoal : Int=80,
    carbDailyGoal : Int=400,
    totalCarb : Int=100,
    totalProtein : Int=100,
    totalFat : Int=50,
) {
    val spacing = LocalSpacing.current

    val proteinGoal by remember(proteinDailyGoal) {
        mutableStateOf(proteinDailyGoal)
    }
    val carbGoal by remember(carbDailyGoal) {
        mutableStateOf(carbDailyGoal)
    }
    val fatGoal by remember(fatDailyGoal) {
        mutableStateOf(fatDailyGoal)
    }


    val protein by remember(totalProtein) {
        mutableStateOf(totalProtein)
    }
    val carb by remember(totalCarb) {
        mutableStateOf(totalCarb)
    }
    val fat by remember(totalFat) {
        mutableStateOf(totalFat)
    }

    val density = LocalDensity.current

    val macros by remember(carbGoal, proteinGoal, fatGoal, carb, protein, fat) {
        mutableStateOf(
            listOf(
                Macro(
                    name = R.string.carbs,
                    color = R.color.carb,
                    totalAmount = carbGoal.toString(),
                    currentAmount = carb.toString(),
                    overflow_color_1 = R.color.carb_overflow_1,
                    overflow_color_2 = R.color.carb_overflow_2
                ),
                Macro(
                    name = R.string.protein,
                    color = R.color.protein,
                    totalAmount = proteinGoal.toString(),
                    currentAmount = protein.toString(),
                    overflow_color_1 = R.color.protein_overflow_1,
                    overflow_color_2 = R.color.protein_overflow_2
                ),
                Macro(
                    name = R.string.fat,
                    color = R.color.fat,
                    totalAmount = fatGoal.toString(),
                    currentAmount = fat.toString(),
                    overflow_color_1 = R.color.fat_overflow_1,
                    overflow_color_2 = R.color.fat_overflow_2
                )
            )
        )
    }

    OverviewCard(
        title = stringResource(R.string.macros),
        showDescription = false,
        height = 260.dp
    ){
        var progressSize by remember{
            mutableStateOf(100.dp)
        }
        Row(
            modifier = Modifier.fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    val width = with(density){ coordinates.size.width.toDp()}
                    progressSize = (width * 0.32f)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            macros.forEach{macro->

                val remainingAmount = macro.totalAmount.toInt() - macro.currentAmount.toInt()

                Column(
                    modifier =Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(macro.name),
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(macro.color)
                    )

                    Box(
                        modifier = Modifier
                            .size(progressSize)
                            .aspectRatio(1f)
                    ) {
                        CircularProgressBar(
                            currentAmount = macro.currentAmount.toFloat(),
                            totalAmount = macro.totalAmount.toFloat(),
                            valueTextStyle = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 26.sp
                            ),
                            valueTextColor = Color.Black,
                            indicationTextStyle = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 12.sp
                            ),
                            indicationtTextColor = Color.DarkGray,
                            size = progressSize,
                            progressColor = colorResource(macro.color),
                            overflowColorBrush = Brush.verticalGradient(
                                colors = listOf(
                                    colorResource(macro.overflow_color_1),
                                    colorResource(macro.overflow_color_1),
                                    colorResource(macro.overflow_color_2),
                                    colorResource(macro.overflow_color_2)
                                ),
                                startY = 0.0f,
                                endY = 15.0f,
                                tileMode = TileMode.Repeated
                            ),
                            showIndication = true,
                            strokeWidth = 20f,
                            chartPrimaryText = macro.currentAmount,
                            chartSecondaryText = "/${macro.totalAmount}g"
                        )
                    }

                    Text(
                        text = if(remainingAmount >= 0) "${abs(remainingAmount)}g left" else "${abs(remainingAmount)}g over",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )
                }

            }
        }
    }
}


data class Macro(
    @StringRes val name : Int,
    val totalAmount : String,
    val currentAmount : String,
    @ColorRes val color : Int,
    @ColorRes val overflow_color_1 : Int,
    @ColorRes val overflow_color_2 : Int
)

