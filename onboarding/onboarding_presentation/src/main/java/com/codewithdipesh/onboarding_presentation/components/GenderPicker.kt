package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R

@Composable
fun GenderPicker(
    text:String,
    size: Dp = 120.dp,
    isSelected :Boolean,
    icon : Painter,
    selectedIcon : Painter,
    color: Color = Color.White,
    selectedColor: Color = Color.Black,
    textColor: Color = Color.Black,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    onSelected:()->Unit,
) {

    val backgroundColor = if (isSelected) selectedColor else color
    val borderColor = if (isSelected) selectedColor else Color.LightGray

    val genderIcon = if(isSelected) selectedIcon else icon
    Column(
        modifier = Modifier.size(size)
    ) {
        Box(
            modifier = Modifier
                .size(size - 20.dp)
                .clip(CircleShape)
                .border(width = 2.dp,color = borderColor, shape = CircleShape)
                .background(backgroundColor)
                .clickable {
                    onSelected()
                }
            ,
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = genderIcon,
                contentDescription = text
            )
        }

        Text(
            text=text,
            style = textStyle,
            color = textColor
        )

    }

}


@Preview(showBackground = true)
@Composable

fun GenderPickerPreview(){
    GenderPicker(
        text = "Male",
        isSelected = true,
        icon = painterResource(id = R.drawable.male_vector_black),
        selectedIcon = painterResource(id = R.drawable.male_vector_white),
        onSelected = {}
    )
}