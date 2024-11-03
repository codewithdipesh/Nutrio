package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R

@Composable
fun OverviewCard(
    title : String,
    color : Color = MaterialTheme.colorScheme.background,
    height : Dp = 200.dp,
    width : Dp,
    titleColor: Color = Color.Black,
    descColor:Color = colorResource(R.color.text_dark_grey),
    description : String? = null,
    cornerShape: RoundedCornerShape = RoundedCornerShape(20.dp),
    showDescription : Boolean = false,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(spacing.spaceLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceLarge)
                .fillMaxSize()
        ) {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.labelLarge
            )
            if (showDescription && description != null) {
                Text(
                    text = description,
                    color = descColor,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = LocalSpacing.current.spaceSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                //calorie progress Bar
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .aspectRatio(1f)
                ) {
                    CircularProgressBar(
                        currentAmount = 3000.toFloat(),
                        totalAmount = 2800.toFloat(),
                        valueTextStyle = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 34.sp
                        ),
                        valueTextColor = Color.Black,
                        indicationTextStyle = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 16.sp
                        ),
                        indicationtTextColor = Color.DarkGray,
                        size = 140.dp,
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
                    AmountWithTitle(
                        title = stringResource(R.string.base_goal),
                        amount = "2,800"
                    )
                    AmountWithTitle(
                        title = stringResource(R.string.food),
                        amount = "2400"
                    )
                    AmountWithTitle(
                        title = stringResource(R.string.exercise),
                        amount = "0"
                    )
                }
            }
        }
        }


}