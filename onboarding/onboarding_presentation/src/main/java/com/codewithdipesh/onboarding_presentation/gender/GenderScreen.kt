package com.codewithdipesh.onboarding_presentation.gender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel(),
    onNavigate :(UiEvent.Navigate) -> Unit,
    onBackNavigate : () -> Unit
) {

    val spacing = LocalSpacing.current

    LaunchedEffect (true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> onBackNavigate()
            }

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            //back button and title
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .wrapContentHeight(),
               verticalArrangement = Arrangement.SpaceBetween,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               BackNavigationButton {
                   viewModel.onBackClick()
               }
               Text(
                   text = stringResource(R.string.select_gender),
                   style = MaterialTheme.typography.labelLarge
               )
           }


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){

                //gender selection
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceMedium),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GenderPicker(
                        text = "Male",
                        isSelected = viewModel.selectedGender is Gender.Male ,
                        icon = painterResource(id = R.drawable.male_vector_black),
                        selectedIcon = painterResource(id = R.drawable.male_vector_white),
                        onSelected = {
                            viewModel.onGenderClick(Gender.Male)
                        }
                    )
                    Spacer(modifier = Modifier.padding(spacing.spaceMedium))
                    GenderPicker(
                        text = "Female",
                        isSelected = viewModel.selectedGender is Gender.Female,
                        icon = painterResource(id = R.drawable.female_symbol),
                        selectedIcon = painterResource(id = R.drawable.female_symbol_white),
                        onSelected = {
                            viewModel.onGenderClick(Gender.Male)
                        }
                    )

                }

                //button
                Row (
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    ActionButton(
                        text = stringResource(R.string.Continue),
                        textColor = Color.White,
                        backgroundColor = Color.Black,
                        width = 150.dp,
                        height = 60.dp,
                        onClick = {
                            viewModel.onNextClick()
                        }
                    )
                }


            }

        }
    }

}