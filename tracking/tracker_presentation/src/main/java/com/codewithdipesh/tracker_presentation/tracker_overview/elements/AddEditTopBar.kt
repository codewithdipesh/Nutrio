package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun AddEditTopBar(
    modifier: Modifier = Modifier,
    onBackNavigate : () -> Unit,
    onDone : () -> Unit,
    title : String
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(colorResource(R.color.bg_color))
            .padding(horizontal = spacing.spaceMedium)
    ) {
        //meal type name
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            //heading
            Row(modifier =Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                //back button
                IconButton(
                    onClick = {
                        onBackNavigate()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow_svg),
                        tint = colorResource(R.color.dark_gray),
                        contentDescription = "Back",
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    text = title,
                    color = Color.Black,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 20.sp
                    )
                )
            }
        }
        //tick icon
        IconButton(
            onClick = {
                onDone()
            },
            modifier =Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Done,
                tint = colorResource(R.color.dark_gray),
                contentDescription = "Done",
                modifier = Modifier.size(28.dp)
            )
        }


    }
}