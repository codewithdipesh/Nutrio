package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.core.R

@Composable
fun CustomOption(
    text:String,
    desc:String,
    isSelected :Boolean,
    selectedColor: Color = Color.Transparent,
    textColor: Color = Color.Black,
    selectedTextColor: Color ,
    descColor: Color,
    selectedDescColor: Color = Color.LightGray,
    borderColor : Color = Color.Black,
    selectedBorderColor:Color= Color.Black,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    onSelected:()->Unit,
) {
    val spacing = LocalSpacing.current

    var backgroundColor = if (isSelected) selectedColor else Color.Transparent

    var textSelectedColor = if (isSelected) selectedTextColor else textColor

    var descSelectedColor = if (isSelected) selectedDescColor else descColor

    var borderSelectedColor = if (isSelected) selectedBorderColor else borderColor



    LaunchedEffect(isSelected) {
        if(isSelected){
            textSelectedColor = selectedTextColor
            backgroundColor = selectedColor
            descSelectedColor = selectedDescColor
            borderSelectedColor = selectedBorderColor
        }else{
            textSelectedColor = textColor
            backgroundColor = selectedColor
            descSelectedColor = descColor
            borderSelectedColor = borderColor
        }
    }

     Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor, RoundedCornerShape(10.dp))
                .border(
                    width = 2.dp,
                    color = borderSelectedColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    onSelected()
                }
            ,
            contentAlignment = Alignment.BottomStart
        ){
           Column(Modifier.padding(spacing.spaceSmall)) {
               Text(
                   text = text,
                   style = textStyle.copy(
                       fontSize = 18.sp
                   ),
                   color = textSelectedColor
               )
               Spacer(Modifier.height(spacing.spaceExtraSmall))
               Text(
                   text= desc,
                   style = textStyle.copy(
                       fontSize = 14.sp
                   ),
                   color = descSelectedColor
               )
           }
        }


}