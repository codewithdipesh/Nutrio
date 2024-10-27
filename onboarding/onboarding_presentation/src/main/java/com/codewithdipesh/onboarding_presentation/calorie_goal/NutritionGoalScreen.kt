package com.codewithdipesh.onboarding_presentation.calorie_goal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.R
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.CalorieCounter
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.CustomChart
import com.codewithdipesh.onboarding_presentation.components.PercentageDonutChart
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import java.time.LocalDate
import java.util.Calendar

@Composable
fun NutritionGoalScreen(
    viewModel: NutritionGoalViewModel = hiltViewModel(),
    onNavigate: (UiEvent.NavigateAndPopUp) -> Unit,
) {
    val calorieGoalAmount by remember(viewModel.calorieGoal) {
        mutableStateOf(viewModel.calorieGoal)
    }
    val spacing = LocalSpacing.current
    LaunchedEffect(Unit) {
        viewModel.calculateCalorie()
    }
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(
                    UiEvent.NavigateAndPopUp(
                        route = event.route,
                        popUpRoute = Route.WELCOME
                    )
                )

                else -> Unit
            }
        }
    }
    ScreenComponent(
        title = stringResource(R.string.nutrient_goal),
        description = stringResource(R.string.nutrient_goal_desc),
        isProgressVisible = false,
        isBackNavEnabled = false,
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {
         PercentageDonutChart(
             firstPercentage = 20f,
             secondPercentage = 50f,
             thirdPercentage = 30f,
             centerText = viewModel.calorieGoal.toString(),
             chartSize = 300.dp
         )

    }
}