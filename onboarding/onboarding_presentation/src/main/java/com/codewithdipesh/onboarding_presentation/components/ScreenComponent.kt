package com.codewithdipesh.onboarding_presentation.components

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

@Composable
fun ScreenComponent(
    title:String,
    onBackClicked : ()->Unit,
    onNextClicked : () -> Unit,
    middleSectionContent : @Composable () -> Unit = {},
    content : @Composable () -> Unit
) {
    val spacing = LocalSpacing.current

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
               Row (modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.Start){
                   BackNavigationButton {
                       onBackClicked()
                   }
               }
               Text(
                   text = title,
                   style = MaterialTheme.typography.labelLarge
               )
           }

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

            middleSectionContent()

            Spacer(modifier = Modifier.fillMaxHeight(0.2f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){

                //composable content
                content()

                //button
                Row (
                    Modifier.fillMaxWidth()
                        .padding(bottom = spacing.spaceMedium),
                    horizontalArrangement = Arrangement.End
                ){
                    ActionButton(
                        text = stringResource(R.string.Continue),
                        textColor = Color.White,
                        backgroundColor = Color.Black,
                        width = 150.dp,
                        height = 60.dp,
                        onClick = {
                            onNextClicked()
                        }
                    )
                }


            }

        }
    }

}