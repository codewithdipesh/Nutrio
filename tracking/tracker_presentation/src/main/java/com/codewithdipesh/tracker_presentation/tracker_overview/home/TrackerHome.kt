package com.codewithdipesh.tracker_presentation.tracker_overview.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalendarRow
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalorieCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CircularProgressBar
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.MacrosCard
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
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        val scope = rememberCoroutineScope()
        val state = rememberPagerState(initialPage = 0 , pageCount = {2})
        //TopBar
        Row (
            modifier = Modifier.fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = spacing.spaceLarge)
                .padding(bottom = spacing.default),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = getStringFromDate(selectedDate),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                modifier = Modifier.clickable {
                    isCalenderVisible = !isCalenderVisible
                }
                .weight(1f)
            )
            Text(
                text = stringResource(R.string.app_name).uppercase(Locale.ENGLISH),
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(R.color.progress_color),
                modifier = Modifier.weight(1f)
            )
            //TODO ADD A SETTINGS OPTION
            Text(
                text = stringResource(R.string.today),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Transparent,
                modifier = Modifier.weight(1f)
            )


        }

        //toggle buton for calorie and macro card
        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceLarge)
                .height(30.dp),
             verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
            ) {
                Box(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        scope.launch {
                            state.animateScrollToPage(0)
                        }
                    },
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AutoResizeText(
                            text = stringResource(R.string.calories),
                            color = if(state.currentPage == 0) colorResource(R.color.progress_color) else Color.Black,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                        )
                        //underline
                        HorizontalDivider(
                            thickness = 4.dp,
                            color = if(state.currentPage == 0) colorResource(R.color.progress_color) else Color.Transparent
                        )
                    }

                }

                Box(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        scope.launch {
                            state.animateScrollToPage(1)
                        }
                    },
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AutoResizeText(
                            text = stringResource(R.string.macros),
                            color = if(state.currentPage == 1) colorResource(R.color.progress_color) else Color.Black,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                        )
                        //underline
                        HorizontalDivider(
                            thickness = 4.dp,
                            color = if(state.currentPage == 1) colorResource(R.color.progress_color) else Color.Transparent
                        )
                    }
            }
        }
        //macro and calorie card pager
        Box(
            modifier = Modifier.wrapContentHeight()
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

    }

    //calendar
    if(isCalenderVisible){
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 60.dp)
                .wrapContentHeight()
        ){
            CalendarRow(
                listofShownDates = viewModel.calendarState.listofShownDates,
                selectedDate = viewModel.calendarState.selectedDate,
                onSelect = {
                    viewModel.onEvent(TrackerOverviewEvent.OnDateSelect(it))
                    isCalenderVisible = false
                },
                onPreviousWeek = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousWeekClick)
                },
                onNextWeek = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextWeekClick)
                }
            )
        }
    }



}