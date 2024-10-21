package com.codewithdipesh.onboarding_presentation.height

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.InchToFt
import com.codewithdipesh.core.util.ftToInch
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.ScrollPicker
import kotlin.math.roundToInt

@Composable
fun FtInchPicker(
    initialHeightInInch : Float,
    onSelectedHeightChange : (Float)->Unit,
){
    val spacing = LocalSpacing.current

    var selectedHeightInInch by remember {
        mutableStateOf(initialHeightInInch)
    }
    // Keep track of feet and inches separately
    var selectedFt by remember {
        mutableStateOf(InchToFt(selectedHeightInInch).toInt())
    }

    var selectedInch by remember {
        mutableStateOf((selectedHeightInInch % 12f).roundToInt())
    }

    LaunchedEffect(selectedHeightInInch) {
        selectedFt = InchToFt(selectedHeightInInch).toInt()
        selectedInch = (selectedHeightInInch % 12f).roundToInt()
    }

    Row( Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        //ft part
        ScrollPicker(
            width = 80.dp,
            itemHeight = 100.dp,
            numberOfDisplayItems = 3,
            //itenms should be in decreasing order bcz ,in the scale up scrolling will increase
            items = (8 downTo 2).toMutableList(),
            initialItem = selectedFt,
            textStyle= MaterialTheme.typography.headlineLarge,
            textColor= Color.LightGray,
            selectedTextColor = Color.Black
        ) {newft->
            selectedFt = newft
            selectedHeightInInch = ftToInch(selectedFt,selectedInch)
            onSelectedHeightChange(selectedHeightInInch)
        }
        Spacer(modifier = Modifier.padding(spacing.spaceExtraSmall))
        Text(
            text = stringResource(R.string.ft),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(spacing.spaceExtraSmall))
        //Inch Part
        ScrollPicker(
            width = 80.dp,
            itemHeight = 100.dp,
            numberOfDisplayItems = 3,
            //itenms should be in decreasing order bcz ,in the scale up scrolling will increase
            items = ( 12 downTo -1).toMutableList(),
            initialItem = selectedInch ,
            textStyle= MaterialTheme.typography.headlineLarge,
            textColor= Color.LightGray,
            selectedTextColor = Color.Black
        ) {newInch ->
            selectedInch = newInch
            selectedHeightInInch = ftToInch(selectedFt,selectedInch)
            onSelectedHeightChange(selectedHeightInInch)

        }
        Spacer(modifier = Modifier.padding(spacing.spaceExtraSmall))
        Text(
            text = stringResource(R.string.inch),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )

    }
}