package com.codewithdipesh.onboarding_presentation.goalPace

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.R
import com.codewithdipesh.core.domain.model.GoalType
import com.codewithdipesh.core.domain.model.WeightPace
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.CustomOption
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import com.codewithdipesh.onboarding_presentation.goal.GoalViewModel

@Composable
fun GoalPaceScreen(
    viewModel: GoalPaceViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
){
    val spacing = LocalSpacing.current

    var selectedPaceType by remember(viewModel.selectedGoalPace){
        mutableStateOf(viewModel.selectedGoalPace)
    }

    LaunchedEffect (true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> onBackNavigate()
            }

        }
    }
    ScreenComponent(
        title = stringResource(R.string.goal_pace),
        description = stringResource(R.string.goal_pace_desc),
        onBackClicked = {
            viewModel.onBackClick()
        },
        currentProgress = 7,
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            CustomOption(
                text = stringResource(R.string.goal_pace_moderate),
                isSelected = selectedPaceType is WeightPace.Moderate,
                onSelected = {
                    viewModel.onGoalPaceSelect(WeightPace.Moderate)
                },
                descColor = Color.DarkGray,
                selectedDescColor = Color.DarkGray,
                textColor = Color.Black,
                selectedTextColor = colorResource(R.color.indicator_color),
                borderColor = Color.DarkGray,
                selectedBorderColor = colorResource(R.color.indicator_color)
            )
            CustomOption(
                text = stringResource(R.string.goal_pace_extreme),
                isSelected = selectedPaceType is WeightPace.Extreme,
                onSelected = {
                    viewModel.onGoalPaceSelect(WeightPace.Extreme)
                },
                descColor = Color.DarkGray,
                selectedDescColor = Color.DarkGray,
                textColor = Color.Black,
                selectedTextColor = colorResource(R.color.indicator_color),
                borderColor = Color.DarkGray,
                selectedBorderColor = colorResource(R.color.indicator_color)
            )

        }

    }
}