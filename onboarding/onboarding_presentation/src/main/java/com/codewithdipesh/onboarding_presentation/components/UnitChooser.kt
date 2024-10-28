package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.height.LengthUnit

@Composable
fun UnitChooser(
    units : List <LengthUnit>,
    height: Dp,
    width: Dp,
    backgroundColor: Color = Color.Transparent,
    selectedColor: Color,
    textColor: Color,
    selectedTextColor: Color,
    selectedUnit : LengthUnit,
    onUnitSelected : (LengthUnit) -> Unit,
) {
    val roundedCornerShapeStarting = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
    val roundedCornerShapeEnding = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
    val roundedCornerShape = RoundedCornerShape(30.dp)
    val spacing = LocalSpacing.current

    var selectedUnitIndex by remember {
        mutableStateOf(selectedUnit)
    }

    Box(modifier = Modifier
        .height(height)
        .width(width * units.size)
        .border(width =2.dp, color = selectedColor , shape = roundedCornerShape)
        .background(backgroundColor),
        contentAlignment = Alignment.Center
        ){
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
           for(unit in units){

               val startElement = unit == units.first()
               val endElement = unit == units.last()
               SingleUnit(
                   unit = unit,
                   height = height - spacing.spaceSmall,
                   width = width - spacing.spaceExtraSmall,
                   shape =
                        if(startElement) roundedCornerShapeStarting
                        else if(endElement) roundedCornerShapeEnding
                        else RoundedCornerShape(0.dp),
                   selectedColor = selectedColor,
                   textColor = textColor,
                   selectedTextColor = selectedTextColor,
                   isSelected = unit == selectedUnitIndex,
                   onUnitSelected = {
                       onUnitSelected(it)
                       selectedUnitIndex = it
                   }
               )

               }
           }


        }

}

@Composable
fun SingleUnit(
    unit : LengthUnit,
    height: Dp,
    width: Dp,
    shape: RoundedCornerShape,
    selectedColor: Color,
    textColor: Color,
    selectedTextColor: Color,
    isSelected : Boolean,
    onUnitSelected : (LengthUnit) -> Unit,
){
    var selected by remember {
        mutableStateOf(isSelected)
    }
    LaunchedEffect(isSelected) {
        selected = isSelected
    }

    Box(modifier = Modifier
        .height(height)
        .width(width)
        .clip(shape)
        .background(
           color = if(selected) selectedColor else Color.Transparent,
        ).clickable {
            onUnitSelected(unit)
        },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = unit.name,
            style = MaterialTheme.typography.displayMedium,
            color = if(selected) selectedTextColor else textColor
        )
    }
}