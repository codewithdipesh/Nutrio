package com.codewithdipesh.onboarding_presentation.gender

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
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R
import com.codewithdipesh.core.domain.model.Gender
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.onboarding_presentation.components.ActionButton
import com.codewithdipesh.onboarding_presentation.components.BackNavigationButton
import com.codewithdipesh.onboarding_presentation.components.GenderPicker
import com.codewithdipesh.onboarding_presentation.components.ScreenComponent

@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel(),
    onNavigate :(UiEvent.Navigate) -> Unit,
    onBackNavigate : () -> Unit
) {

    val spacing = LocalSpacing.current
    val selectedGender by remember(viewModel.selectedGender) {
        mutableStateOf(viewModel.selectedGender)
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
        title = stringResource(R.string.select_gender),
        onBackClicked = {
           viewModel.onBackClick()
        },
        currentProgress = 1,
        onNextClicked = {
            viewModel.onNextClick()
        }
    ) {

        //gender Picker
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            horizontalArrangement = Arrangement.Center
        ) {
            GenderPicker(
                text = "Male",
                isSelected = selectedGender is Gender.Male,
                icon = painterResource(id = R.drawable.male_vector_black),
                onSelected = {
                    viewModel.onGenderClick(Gender.Male)
                }
            )
            Spacer(modifier = Modifier.padding(spacing.spaceMedium))
            GenderPicker(
                text = "Female",
                isSelected = selectedGender is Gender.Female,
                icon = painterResource(id = R.drawable.female_symbol),
                onSelected = {
                    viewModel.onGenderClick(Gender.Female)
                }
            )

        }
    }

}





