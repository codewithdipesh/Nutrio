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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core_ui.components.AutoResizeText
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CalorieCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CircularProgressBar
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.MacrosCard
import kotlinx.coroutines.launch

@Composable
fun TrackerHome(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        val spacing = LocalSpacing.current
        val scope = rememberCoroutineScope()
        val state = rememberPagerState(initialPage = 0 , pageCount = {2})

        Box(
            modifier = Modifier.size(300.dp,100.dp)
                .padding(spacing.spaceLarge)
                .background(colorResource(R.color.button_color), RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
        ){
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceSmall),
             verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(spacing.spaceMedium))
                    .background(
                        color = if(state.currentPage == 0) MaterialTheme.colorScheme.background else Color.Transparent,
                        shape = RoundedCornerShape(spacing.spaceMedium)
                    )
                    .clickable {
                        scope.launch {
                            state.animateScrollToPage(0)
                        }
                    },
                    contentAlignment = Alignment.Center
                ){
                    AutoResizeText(
                        text = "Calories",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                    )
                }

                Box(modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(spacing.spaceMedium))
                    .background(
                        color = if(state.currentPage == 1) MaterialTheme.colorScheme.background else Color.Transparent,
                        shape = RoundedCornerShape(spacing.spaceMedium)
                    )
                    .clickable {
                        scope.launch {
                            state.animateScrollToPage(1)
                        }
                    },
                    contentAlignment = Alignment.Center
                ){
                    AutoResizeText(
                        text = "Macros",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }


            }
        }

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

}