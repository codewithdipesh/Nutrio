package com.codewithdipesh.onboarding_presentation.activity_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

    val activityOptions = remember {
        listOf(
            ActivityOptionUiState(
                titleRes = R.string.sedentary,
                descriptionRes = R.string.sedentary_desc,
                activityLevel = ActivityLevel.Sedentary
            ),
            ActivityOptionUiState(
                titleRes = R.string.light,
                descriptionRes = R.string.light_desc,
                activityLevel = ActivityLevel.Light
            ),
            ActivityOptionUiState(
                titleRes = R.string.moderate,
                descriptionRes = R.string.moderate_desc,
                activityLevel = ActivityLevel.Moderate
            ),
            ActivityOptionUiState(
                titleRes = R.string.active,
                descriptionRes = R.string.active_desc,
                activityLevel = ActivityLevel.Active
            ),
            ActivityOptionUiState(
                titleRes = R.string.very_active,
                descriptionRes = R.string.very_active_desc,
                activityLevel = ActivityLevel.VeryActive
            )
        )
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
        currentProgress = 5,
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            items(activityOptions){option->
                CustomOption(
                    text = stringResource(option.titleRes),
                    desc = stringResource(option.descriptionRes),
                    isSelected = selectedActivityLevel == option.activityLevel,
                    onSelected = {
                        viewModel.onActivitySelect(option.activityLevel)
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

    }
