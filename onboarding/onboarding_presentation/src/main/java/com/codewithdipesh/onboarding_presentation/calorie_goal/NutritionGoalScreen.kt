package com.codewithdipesh.onboarding_presentation.calorie_goal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.colorResource
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
import com.codewithdipesh.onboarding_presentation.components.NutrientLabel
import com.codewithdipesh.onboarding_presentation.components.PercentageDonutChart
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import java.time.LocalDate
import java.util.Calendar

@Composable
fun NutritionGoalScreen(
    viewModel: NutritionGoalViewModel = hiltViewModel(),
    onNavigateAndPopUp: (UiEvent.NavigateAndPopUp) -> Unit,
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
                is UiEvent.NavigateAndPopUp -> onNavigateAndPopUp(
                    UiEvent.NavigateAndPopUp(
                        route = event.route
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
             firstPercentage = 50f,
             secondPercentage = 30f,
             thirdPercentage = 20f,
             centerText = viewModel.calorieGoal.toString(),
             chartSize = 300.dp
         )
         Spacer(Modifier.height(spacing.spaceExtraLarge))
         Row (
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.SpaceEvenly ,
             verticalAlignment = Alignment.CenterVertically
         ){
             NutrientLabel(
                 nutrientName = "carb",
                 color = colorResource(R.color.carb),
                 textColor = Color.Black
             )
             NutrientLabel(
                 nutrientName = "protein",
                 color = colorResource(R.color.protein),
                 textColor = Color.Black
             )
             NutrientLabel(
                 nutrientName = "fat",
                 color = colorResource(R.color.fat),
                 textColor = Color.Black
             )
         }

    }
}