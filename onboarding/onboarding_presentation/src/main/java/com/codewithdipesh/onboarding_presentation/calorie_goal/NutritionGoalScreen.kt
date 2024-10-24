package com.codewithdipesh.onboarding_presentation.calorie_goal

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.R
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.CalorieCounter
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent

@Composable
fun NutritionGoalScreen(
    viewModel: NutritionGoalViewModel = hiltViewModel(),
    onNavigate: (UiEvent.NavigateAndPopUp) -> Unit,
) {
    val calorieGoalAmount by remember(viewModel.calorieGoal) {
        mutableStateOf(viewModel.calorieGoal)
    }
    LaunchedEffect(Unit) {
        viewModel.calculateCalorie()
    }
    LaunchedEffect(true){
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(UiEvent.NavigateAndPopUp(
                    route = event.route,
                    popUpRoute = Route.NUTRIENT_GOAL
                ))
                else -> Unit
            }
        }
    }
    ScreenComponent(
        title = stringResource(R.string.nutrient_goal),
        isProgressVisible = false,
        isBackNavEnabled = false,
        onNextClicked = {
            viewModel.onNextClick()
        },
        middleSectionContent = {
            Icon(
                painter = painterResource(id = R.drawable.fire),
                contentDescription = stringResource(id = R.string.nutrient_goal)
            )
        }
    ) {
         Text(
             text = "${calorieGoalAmount} Cal",
             style = MaterialTheme.typography.displayLarge,
             color = Color.Black
         )

    }
}