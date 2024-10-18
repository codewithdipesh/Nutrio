package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun ActionButton(
    text:String,
    onClick:()->Unit,
    height: Dp = 64.dp,
    width: Dp = 280.dp,
    textColor :Color = Color.Black,
    backgroundColor: Color = Color.White,
    isEnabled :Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium
) {

    Box(
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(32.dp))
            .background(backgroundColor)
            .clickable {
                if(isEnabled){
                    onClick()
                }
            }
        ,
        contentAlignment = Alignment.Center

    ){
            Text(
                text = text,
                style = textStyle,
                color = textColor ,
                modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
            )

    }

}