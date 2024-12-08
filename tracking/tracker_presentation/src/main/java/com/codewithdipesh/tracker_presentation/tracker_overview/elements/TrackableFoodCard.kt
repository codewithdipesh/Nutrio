package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TrackableFoodCard(
    onClick: (TrackableFood) -> Unit,
    onAdd : (TrackableFood)-> Unit,
    modifier: Modifier = Modifier,
    food: TrackableFood
) {
    val spacing = LocalSpacing.current
    val scope = rememberCoroutineScope()

    val calorieAmount = food.nutrients[com.codewithdipesh.tracker_domain.model.Unit.Gm100]!!.calories

    var iconSize by remember {
        mutableStateOf(50.dp)
    }
    var isClicked by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(
        visible = true,
        enter =  fadeIn(
            animationSpec = tween(2000)
        ),
        exit = fadeOut(
            animationSpec = tween(2000)
        )
    ) {
        Box(modifier =modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(15.dp))
            .background(
                color =
                if(isClicked) colorResource(R.color.progress_color).copy(0.2f) else colorResource(R.color.light_gray),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable (
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){
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
                    modifier =Modifier.size(iconSize),
                    contentAlignment = Alignment.Center
                ){
                    //animated icon after clicking
                    androidx.compose.animation.AnimatedVisibility(
                        visible = true,
                        enter = scaleIn(
                            animationSpec = tween(2000),
                            initialScale = 0f
                        ) + fadeIn(
                            initialAlpha = 0f
                        ),
                        exit = scaleOut(
                            animationSpec = tween(1500),
                            targetScale = 0f
                        ) + fadeOut(
                            animationSpec = tween(2000),
                            targetAlpha = 0f
                        )
                    ) {

                        Box(
                            modifier = Modifier
                                .size(
                                    if(isClicked) iconSize
                                    else iconSize * 0.7f
                                )
                                .clip(RoundedCornerShape(30.dp))
                                .background(
                                    color = if(isClicked) colorResource(R.color.progress_color).copy(0.6f)
                                    else colorResource(R.color.text_grey),
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .clickable {
                                    if(!isClicked){
                                        isClicked = true
                                        onAdd(food)
                                        scope.launch {
                                            delay(2000)
                                            isClicked = false
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                imageVector = if(isClicked) Icons.Filled.Done else Icons.Filled.Add,
                                tint = if(isClicked) Color.White else colorResource(R.color.progress_color),
                                contentDescription = "Adding ${food.name}"
                            )
                        }
                    }
                }


            }
        }
    }

}