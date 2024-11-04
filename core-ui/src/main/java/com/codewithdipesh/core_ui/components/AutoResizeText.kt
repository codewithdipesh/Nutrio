package com.codewithdipesh.core_ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizeText(
    text: String,
    color: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
    val defaultFontSize = MaterialTheme.typography.titleSmall.fontSize
    var resizedTextStyle by remember(style) {
        mutableStateOf(
            if (style.fontSize.isUnspecified) {
                style.copy(fontSize = defaultFontSize)
            } else {
                style
            }
        )
    }

    var shouldDraw by remember { mutableStateOf(true) }
    var shrinkCounter by remember { mutableStateOf(0) }

    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        softWrap = false,
        maxLines = 1,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (shrinkCounter < 20) {
                    shouldDraw = false
                    shrinkCounter++
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = resizedTextStyle.fontSize * 0.9f
                    )
                } else {
                    shouldDraw = true
                }
            } else {
                shouldDraw = true
            }
        },
        style = resizedTextStyle
    )
}