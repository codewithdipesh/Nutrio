package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun TrackableFoodCard(
    onClick: (TrackableFood) -> Unit,
    onAdd : (TrackableFood)-> Unit,
    modifier: Modifier = Modifier,
    food: TrackableFood
) {
    val spacing = LocalSpacing.current

    val calorieAmount = food.nutrients[com.codewithdipesh.tracker_domain.model.Unit.Gm100]!!.calories

    Box(modifier =modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(15.dp))
        .background(
            color = colorResource(R.color.light_gray),
            shape = RoundedCornerShape(15.dp)
        )
        .clickable {
            onClick(food)
        }
    ){
        Row (
            modifier =Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            //Chapati
            //100 cal, 100 gram
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = food.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(R.color.dark_gray)
                )
                Text(
                    text = "${calorieAmount} cal, 100 gram",
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(R.color.medium_gray)
                )
            }

            //plus icon for navigating to addOrEdit screen
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        color = colorResource(R.color.text_grey),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        onAdd(food)
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = colorResource(R.color.progress_color),
                    contentDescription = "Adding ${food.name}"
                )
            }
        }
    }
}