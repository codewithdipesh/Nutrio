package com.codewithdipesh.tracker_presentation.tracker_overview.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_presentation.tracker_overview.model.TrackerOverviewEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalendarView
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalorieCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.FoodCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.MacrosCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.ToggleButtons
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackerHome(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
){

    val spacing = LocalSpacing.current
    var isCalenderVisible by remember {
        mutableStateOf(false)
    }
    val selectedDate by remember(viewModel.state){
        mutableStateOf(viewModel.state.date)
    }
    val scrollState = rememberScrollState()
    val state = rememberPagerState(initialPage = 0 , pageCount = {2})

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(UiEvent.Navigate(event.route))
                else -> {}
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            //Topbar
            TopBar(
                selectedDate = selectedDate,
                onDateClick = {
                    isCalenderVisible = !isCalenderVisible
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
                .padding(vertical = spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            //toggle buton for calorie and macro card
            ToggleButtons(state)
            //macro and calorie card pager
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ){
                HorizontalPager(
                    state = state,
                    modifier = Modifier.fillMaxWidth(),
                    flingBehavior = PagerDefaults.flingBehavior(
                        state = state,
                        pagerSnapDistance = PagerSnapDistance.atMost(0)
                    )
                ) {
                    when(it){
                        0 -> CalorieCard(
                            totalCalories = viewModel.state.totalCalories ?: 0,
                            caloriesGoal = viewModel.state.calorieGoal ?: 1,
                            //TODO calories burned
                        )
                        1 -> MacrosCard(
                            totalCarb = viewModel.state.totalCarbs.toInt() ?:0,
                            totalProtein = viewModel.state.totalProteins.toInt() ?: 0,
                            totalFat = viewModel.state.totalFats.toInt() ?:0,
                            carbDailyGoal = viewModel.state.carbsGoal?:1,
                            proteinDailyGoal = viewModel.state.proteinsGoal?:1,
                            fatDailyGoal = viewModel.state.fatsGoal?:1
                        )
                    }

                }
            }

            //meals
            viewModel.state.meals.forEach{meal ->
                    FoodCard(
                        meal = meal,
                        totalCalories = meal.calories,
                        listOfFoods = viewModel.state.trackedFoods.filter { it.mealType == meal.mealType },
                        onAddClick = {viewModel.onEvent(TrackerOverviewEvent.OnAddFoodClick(meal.mealType))},
                        onItemClick = {viewModel.onEvent(TrackerOverviewEvent.OnFoodClick(it))},
                        onExpandClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal)) }
                    )
            }
        }

        //calendar
        if(isCalenderVisible) {
            CalendarView(
                onCalendarClose = {isCalenderVisible = false},
                viewModel = viewModel
            )
        }
    }

}