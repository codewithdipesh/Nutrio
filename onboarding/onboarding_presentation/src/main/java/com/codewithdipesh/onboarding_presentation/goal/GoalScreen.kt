package com.codewithdipesh.onboarding_presentation.goal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.codewithdipesh.core.domain.model.ActivityLevel
import com.codewithdipesh.core.domain.model.GoalType
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.CustomOption
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import com.codewithdipesh.onboarding_presentation.height.HeightViewModel

@Composable
fun GoalScreen(
    viewModel: GoalViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
){
    val spacing = LocalSpacing.current

    var selectedGoalType by remember(viewModel.selectedGoalType){
        mutableStateOf(viewModel.selectedGoalType)
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
        title = stringResource(R.string.goal),
        description = stringResource(R.string.goal_desc),
        onBackClicked = {
            viewModel.onBackClick()
        },
        currentProgress = 6,
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
                text = stringResource(R.string.gain),
                desc = stringResource(R.string.gain_desc),
                isSelected = selectedGoalType is GoalType.GainWeight,
                onSelected = {
                    viewModel.onGoalSelect(GoalType.GainWeight)
                },
                descColor = Color.DarkGray,
                selectedDescColor = Color.DarkGray,
                textColor = Color.Black,
                selectedTextColor = colorResource(R.color.indicator_color),
                borderColor = Color.DarkGray,
                selectedBorderColor = colorResource(R.color.indicator_color)
            )
            CustomOption(
                text = stringResource(R.string.keep),
                desc = stringResource(R.string.keep_desc),
                isSelected = selectedGoalType is GoalType.KeepWeight,
                onSelected = {
                    viewModel.onGoalSelect(GoalType.KeepWeight)
                },
                descColor = Color.DarkGray,
                selectedDescColor = Color.DarkGray,
                textColor = Color.Black,
                selectedTextColor = colorResource(R.color.indicator_color),
                borderColor = Color.DarkGray,
                selectedBorderColor = colorResource(R.color.indicator_color)
            )
            CustomOption(
                text = stringResource(R.string.lose),
                desc = stringResource(R.string.lose_desc),
                isSelected = selectedGoalType is GoalType.LoseWeight,
                onSelected = {
                    viewModel.onGoalSelect(GoalType.LoseWeight)
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