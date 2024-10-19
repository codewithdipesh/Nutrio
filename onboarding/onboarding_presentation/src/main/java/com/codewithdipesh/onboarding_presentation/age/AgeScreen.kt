package com.codewithdipesh.onboarding_presentation.age

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent
import com.codewithdipesh.onboarding_presentation.components.ScrollPicker

@Composable
fun AgeScreen(
    viewModel: AgeViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onBackNavigate : ()->Unit
) {
    val localSpacing = LocalSpacing.current
    val selectedAge by remember {
        mutableStateOf(viewModel.age)
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
        title = stringResource(R.string.whats_your_age),
        onBackClicked = {
            viewModel.onBackClick()
        },
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {
        ScrollPicker(
            width = 80.dp,
            itemHeight = 100.dp,
            numberOfDisplayItems = 3,
            //itenms should be in decreasing order bcz ,in the scale up scrolling will increase
            items = (60 downTo 14).toMutableList(),
            initialItem = selectedAge,
            textStyle= MaterialTheme.typography.headlineLarge,
            textColor= Color.LightGray,
           selectedTextColor =Color.Black
        ) {age->
            viewModel.onAgeSelect(age)
        }

    }

}