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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.SearchBar
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.TrackableFoodCard
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUiEvent
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFood(
    viewModel: SearchViewModel = hiltViewModel(),
    mealType : MealType = MealType.Breakfast,
    date:LocalDate = LocalDate.now(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
) {
    val spacing = LocalSpacing.current
    val snackBarHostState = remember { SnackbarHostState() }

    var selectedMeal by remember(viewModel.state.mealType) {
        mutableStateOf(viewModel.state.mealType)
    }
    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    val isLoading by remember(viewModel.state.isLoading) {
        mutableStateOf(viewModel.state.isLoading)
    }

    val result by remember(viewModel.state.result) {
        mutableStateOf(viewModel.state.result)
    }

    LaunchedEffect(Unit) {
        viewModel.setDate(date)
        viewModel.setMealType(mealType)
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.Navigate -> onNavigate(UiEvent.Navigate(event.route))
                is UiEvent.showSnackBar ->{
                  snackBarHostState.showSnackbar(
                      message = event.msg
                  )
                }
                else -> onBackNavigate()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
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
                            viewModel.onEvent(SearchUiEvent.OnBackNavigate)
                        },
                    modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow_svg),
                            tint = colorResource(R.color.dark_gray),
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                SearchBar(
                    onInputChanged = {
                        viewModel.onEvent(SearchUiEvent.OnQueryChange(it))
                        //clear the result
                        viewModel.clearResult()
                                     },
                    onSearch = {viewModel.onEvent(SearchUiEvent.OnSearch)},
                    onClear = {viewModel.onEvent(SearchUiEvent.OnClear)}
                )

                Spacer(modifier = Modifier.height(spacing.spaceMedium))

                if(isLoading){
                    Box(Modifier.fillMaxSize(0.6f),
                        contentAlignment = Alignment.TopCenter
                    ){
                        CircularProgressIndicator(
                            modifier =Modifier.size(50.dp),
                            color = colorResource(R.color.progress_color)
                        )
                    }
                }else{
                    result?.let {
                        TrackableFoodCard(
                            onClick = {
                                //TODO
                            },
                            onAdd = {
                                viewModel.onEvent(SearchUiEvent.onAddFoodClick(it))
                            },
                            food = it
                        )
                    }
                }

            }
            if(isDialogOpen){
                Box(
                    Modifier.fillMaxSize()
                        .background(
                            color = Color.Black.copy(alpha = 0.1f)
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
                                        viewModel.onEvent(SearchUiEvent.OnMealTypeChange(meal))
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