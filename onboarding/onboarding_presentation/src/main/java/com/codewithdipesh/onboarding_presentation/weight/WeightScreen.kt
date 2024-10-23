package com.codewithdipesh.onboarding_presentation.weight

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import com.codewithdipesh.core.domain.model.WeightCategory
import com.codewithdipesh.core.util.calculateBmi
import com.codewithdipesh.core.util.getBmiCategory
import com.codewithdipesh.onboarding_presentation.age.AgeViewModel
import com.codewithdipesh.onboarding_presentation.components.ScaleStyle
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import com.codewithdipesh.onboarding_presentation.components.WeightPicker

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun WeightScreen (
    viewModel: WeightViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
){
    val spacing = LocalSpacing.current
    val configuration = LocalConfiguration.current
    val screenWidth = with(configuration) {screenWidthDp.dp }

    var selectedWeight by remember {
        mutableStateOf(viewModel.selectedWeight)
    }
    var bmi by remember {
        mutableStateOf(viewModel.bmi)
    }
    var bmiCategory by remember {
        mutableStateOf( getBmiCategory(bmi))
    }

    LaunchedEffect(viewModel.selectedWeight) {
        selectedWeight = viewModel.selectedWeight
        bmi = viewModel.bmi
        bmiCategory = getBmiCategory(bmi)
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> onBackNavigate()
            }
        }
    }

    ScreenComponent(
        title = stringResource(R.string.whats_your_weight),
        onBackClicked = {
            viewModel.onBackClick()
        },
        currentProgress = 4,
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {
        Column (
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceLarge),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Text
                Text(
                    text = selectedWeight.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                //Kg
                Text(
                    text = stringResource(R.string.kg),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(spacing.spaceMedium))

            //weight picker
            WeightPicker(
                minWeight = 20f,
                maxWeight = 150f,
                style = ScaleStyle(scaleWidth = screenWidth),
                initialWeight = selectedWeight,
                backgroundColor = MaterialTheme.colorScheme.background
            ) {
                viewModel.onWeightSelect(it)
            }

            Spacer(Modifier.height(spacing.spaceLarge))

            //Bmi
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.grey), RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
            ) {
               Column(
                   Modifier
                       .wrapContentSize()
                       .padding(spacing.spaceMedium)) {
                   Row(){

                       Text(
                           text = stringResource(R.string.your_bmi),
                           style = MaterialTheme.typography.labelSmall.copy(
                               fontSize = 14.sp
                           ),
                           color = Color.Black
                       )
                       Text(
                           text = "(${bmiCategory.name})",
                           style = MaterialTheme.typography.labelSmall.copy(
                               fontSize = 14.sp
                           ),
                           color = when(bmiCategory){
                               WeightCategory.Underweight -> colorResource(R.color.bmi_underweight)
                               WeightCategory.Normal -> colorResource(R.color.bmi_normal)
                               WeightCategory.OverWeight -> colorResource(R.color.bmi_overweight)
                               WeightCategory.Obesse -> colorResource(R.color.bmi_obesse)
                           }
                       )
                   }
                   Spacer(Modifier.height(spacing.spaceSmall))

                   Text(
                       text = bmi.toString(),
                       style = MaterialTheme.typography.headlineLarge.copy(
                           fontSize = 22.sp
                       ),
                       color = when(bmiCategory){
                           WeightCategory.Underweight -> colorResource(R.color.bmi_underweight)
                           WeightCategory.Normal -> colorResource(R.color.bmi_normal)
                           WeightCategory.OverWeight -> colorResource(R.color.bmi_overweight)
                           WeightCategory.Obesse -> colorResource(R.color.bmi_obesse)
                       }
                   )
               }
            }
        }
    }


}

