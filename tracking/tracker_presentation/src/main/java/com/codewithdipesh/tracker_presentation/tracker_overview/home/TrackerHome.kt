package com.codewithdipesh.tracker_presentation.tracker_overview.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.getStringFromDate
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core_ui.components.AutoResizeText
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalendarRow
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalendarView
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalorieCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CircularProgressBar
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.FoodCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.MacrosCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.ToggleButtons
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.TopBar
import dagger.Lazy
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackerHome(
    viewModel: TrackerOverviewViewModel = hiltViewModel()
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
                        0 -> CalorieCard()
                        1 -> MacrosCard()
                    }

                }
            }

            //meals
            viewModel.state.meals.forEach{meal ->
                    FoodCard(
                        meal = meal,
                        totalCalories = meal.calories,
                        listOfFoods = emptyList(),
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