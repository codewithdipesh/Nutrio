package com.codewithdipesh.nutrio.ui.theme
import com.codewithdipesh.nutrio.R

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val customFont = FontFamily(
    Font(R.font.jost_medium, FontWeight.Normal),
    Font(R.font.jost_bold, FontWeight.Bold),
    Font(R.font.jost_bold_italic, FontWeight.Bold, FontStyle.Italic)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 38.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    labelLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    labelSmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    displayMedium = TextStyle(
       fontFamily = customFont,
       fontWeight = FontWeight.Normal,
       fontSize = 16.sp
    ),
    displayLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    titleSmall = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    titleLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 26.sp
    )
)