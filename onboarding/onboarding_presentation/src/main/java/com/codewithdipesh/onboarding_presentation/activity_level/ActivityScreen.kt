package com.codewithdipesh.onboarding_presentation.activity_level

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
import com.codewithdipesh.core.domain.model.ActivityLevel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.CustomOption
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
) {

    val spacing = LocalSpacing.current

    var selectedActivityLevel by remember(viewModel.selectedActivityLevel){
        mutableStateOf(viewModel.selectedActivityLevel)
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
        title = stringResource(R.string.activity_level),
        onBackClicked = {
            viewModel.onBackClick()
        },
        description = stringResource(R.string.activity_level_desc),
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
                text = stringResource(R.string.low),
                desc = stringResource(R.string.low_desc),
                isSelected = selectedActivityLevel is ActivityLevel.Low,
                onSelected = {
                    viewModel.onActivitySelect(ActivityLevel.Low)
                },
                descColor = Color.DarkGray,
                selectedDescColor = Color.DarkGray,
                textColor = Color.Black,
                selectedTextColor = colorResource(R.color.indicator_color),
                borderColor = Color.DarkGray,
                selectedBorderColor = colorResource(R.color.indicator_color)
            )
            CustomOption(
                text = stringResource(R.string.medium),
                desc = stringResource(R.string.medium_desc),
                isSelected = selectedActivityLevel is ActivityLevel.Medium,
                onSelected = {
                    viewModel.onActivitySelect(ActivityLevel.Medium)
                },
                descColor = Color.DarkGray,
                selectedDescColor = Color.DarkGray,
                textColor = Color.Black,
                selectedTextColor = colorResource(R.color.indicator_color),
                borderColor = Color.DarkGray,
                selectedBorderColor = colorResource(R.color.indicator_color)
            )
            CustomOption(
                text = stringResource(R.string.high),
                desc = stringResource(R.string.high_desc),
                isSelected = selectedActivityLevel is ActivityLevel.High,
                onSelected = {
                    viewModel.onActivitySelect(ActivityLevel.High)
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
