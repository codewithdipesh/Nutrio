package com.codewithdipesh.onboarding_presentation.weight

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
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

    LaunchedEffect(viewModel.selectedWeight) {
        selectedWeight = viewModel.selectedWeight
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
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {
        Column (
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = spacing.spaceLarge),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
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
            WeightPicker (
                minWeight = 20f,
                maxWeight = 150f,
                style = ScaleStyle(scaleWidth = screenWidth ),
                initialWeight = selectedWeight,
                backgroundColor = MaterialTheme.colorScheme.background
            ){
                viewModel.onWeightSelect(it)
            }
        }

    }


}

