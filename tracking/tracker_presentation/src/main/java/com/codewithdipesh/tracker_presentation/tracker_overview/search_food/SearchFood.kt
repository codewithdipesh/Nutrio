package com.codewithdipesh.tracker_presentation.tracker_overview.search_food

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import com.codewithdipesh.tracker_presentation.tracker_overview.model.TrackerOverviewEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.model.defaultMeals

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFood(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    mealType : MealType = MealType.Breakfast,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    var selectedMeal by remember {
        mutableStateOf(mealType)
    }

    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.Navigate -> onNavigate(UiEvent.Navigate(event.route))
                else -> onBackNavigate()
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(colorResource(R.color.bg_color))
                    .padding(horizontal = spacing.spaceMedium),
                contentAlignment = Alignment.Center
            ) {
                //back button
                IconButton(
                        onClick = {
                            viewModel.onEvent(TrackerOverviewEvent.OnBackNavigate)
                        },
                    modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow_svg),
                            contentDescription = "Back",
                            modifier = Modifier.size(28.dp)
                        )



                    }
                //meal type name
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.align(Alignment.Center)
                        .clickable {
                            isDialogOpen = !isDialogOpen
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = selectedMeal.name ,
                            color = colorResource(R.color.progress_color),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Normal
                            ),
                        )
                        Text(
                            text = "▼" ,
                            color = colorResource(R.color.progress_color),
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                }


                }

        }
    ) {
        Box(
            modifier =  Modifier.fillMaxSize()
                .padding(it)
        ){
            Text("Testing")
            if(isDialogOpen){
                Box(
                    Modifier.fillMaxSize()
                        .background(
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                          isDialogOpen =false
                        },
                    contentAlignment = Alignment.TopCenter
                ){
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .wrapContentHeight()
                            .background(Color.White,RoundedCornerShape(15.dp))
                            .clip(RoundedCornerShape(15.dp))
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                //do nothing means box will be opened
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            arrayOf(
                                MealType.Breakfast,
                                MealType.Lunch,
                                MealType.Dinner,
                                MealType.Snack).forEach { meal ->
                                Box(Modifier.wrapContentSize()
                                    .clickable {
                                        selectedMeal = meal
                                        isDialogOpen = false
                                    }
                                ){
                                    Text(
                                        text = meal.name,
                                        style = MaterialTheme.typography.displayMedium,
                                        modifier = Modifier.padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceMedium)
                                    )
                                    HorizontalDivider(
                                        color = Color.LightGray,
                                        thickness = 1.dp
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}