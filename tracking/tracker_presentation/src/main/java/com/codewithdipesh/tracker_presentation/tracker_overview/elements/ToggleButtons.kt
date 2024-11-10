package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core_ui.components.AutoResizeText
import kotlinx.coroutines.launch

@Composable
fun ToggleButtons(
    state : PagerState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val scope = rememberCoroutineScope()

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
}