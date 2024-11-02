package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.style.TextAlign
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
    isBackNavEnabled : Boolean = true,
    isProgressVisible : Boolean = true,
    totalProgress : Int = 7,
    currentProgress : Int=0 ,
    description : String? = null,
    descriptionColor : Color = Color.Gray,
    onBackClicked : ()->Unit = {},
    onNextClicked : () -> Unit = {},
    middleSectionContent : @Composable () -> Unit = {},
    content : @Composable () -> Unit
) {
    val spacing = LocalSpacing.current
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
            .background(MaterialTheme.colorScheme.background)
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
               Box(
                   modifier = Modifier.fillMaxWidth()
               ) {
                   // Back button aligned to start
                   if(isBackNavEnabled){
                       BackNavigationButton(
                           modifier = Modifier.align(Alignment.CenterStart),
                           onBackClick = onBackClicked
                       )
                   }
                   // Progress bar centered
                   if(isProgressVisible){
                       ProgressBar(
                           modifier = Modifier.align(Alignment.Center),
                           width = 200.dp,
                           stroke = 4.dp,
                           totalProgress = totalProgress,
                           currentProgress = currentProgress
                       )
                   }
               if(!isProgressVisible && !isBackNavEnabled){
                   Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
               }
               }
               Text(
                   text = title,
                   style = MaterialTheme.typography.labelLarge,
                   textAlign = TextAlign.Center
               )
               Spacer(Modifier.height(spacing.spaceSmall))
               if(description != null){
                   Text(
                       text = description,
                       style = MaterialTheme.typography.labelMedium,
                       color = descriptionColor
                   )
               }
           }
            //no description then padding
            if(description == null){
                Spacer(modifier = Modifier.fillMaxHeight(0.15f))
            }else{
                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            middleSectionContent()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){

                //composable content
                content()

            }
        }

        //button
        Box (
            Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = spacing.spaceMedium),
            contentAlignment = Alignment.BottomEnd
        ){
            ActionButton(
                text = stringResource(R.string.Continue),
                textColor = Color.White,
                backgroundColor = colorResource(R.color.button_color),
                width = 150.dp,
                height = 60.dp,
                onClick = {
                    onNextClicked()
                }
            )
        }

    }

}