package com.codewithdipesh.onboarding_presentation.height

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.CmToInch
import com.codewithdipesh.core.util.InchToCm
import com.codewithdipesh.core.util.InchToFt
import com.codewithdipesh.core.util.ftToInch
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import com.codewithdipesh.onboarding_presentation.components.ScrollPicker
import com.codewithdipesh.onboarding_presentation.components.UnitChooser
import kotlin.math.roundToInt

@Composable
fun HeightScreen(
    viewModel: HeightViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
) {
    val spacing = LocalSpacing.current

    var selectedUnit by remember {
        mutableStateOf(LengthUnit.ft)
    }

    var selectedHeightInInch by remember {
        mutableStateOf(viewModel.selectedHeightInInch)
    }
    LaunchedEffect(viewModel.selectedHeightInInch){
        selectedHeightInInch = viewModel.selectedHeightInInch
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
        title = stringResource(R.string.whats_your_height),
        onBackClicked = {
            viewModel.onBackClick()
        },
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {

        Column(
             modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //unitChooser

            UnitChooser(
                units = listOf(LengthUnit.ft, LengthUnit.cm),
                textColor = Color.Black,
                selectedTextColor = Color.White,
                height = 50.dp,
                width = 70.dp,
                selectedUnit = selectedUnit,
                selectedColor = colorResource(R.color.indicator_color),
                onUnitSelected = { unit ->
                    selectedUnit = unit
                }
            )

            Spacer(Modifier.height(spacing.spaceMedium))

            if (selectedUnit == LengthUnit.ft) {

               FtInchPicker(
                   initialHeightInInch = selectedHeightInInch ,
                   onSelectedHeightChange = {
                       viewModel.onHeightSelect(it)
                   }
               )

            }else{
                Spacer(Modifier.height(spacing.spaceMedium))
                CmHeightInput(
                    minHeight = 36,
                    maxHeight = 241,
                    initialHeightInInch = selectedHeightInInch ,
                    onHeightChange = {
                        viewModel.onHeightSelect(it)
                    }
                )

            }

        }
    }



}


