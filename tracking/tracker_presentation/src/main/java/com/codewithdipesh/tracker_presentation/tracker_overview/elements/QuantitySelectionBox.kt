package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_domain.model.MealType

@Composable
fun QuantitySelectionBox(
    modifier: Modifier = Modifier,
    onDismiss : ()-> Unit,
    amount : Double,
    alignment: Alignment = Alignment.Center,
    onSave : (Double,com.codewithdipesh.tracker_domain.model.Unit)->Unit,
) {
    var selectedAmount by remember(amount) {
        mutableStateOf(amount.toString())
    }
    val controller = LocalSoftwareKeyboardController.current
    val spacing = LocalSpacing.current
    Box(
        Modifier.fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.1f)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onDismiss()
            }
            .then(modifier),
        contentAlignment = alignment
    ){
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White, RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    //do nothing means box will be opened
                }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = spacing.spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(spacing.spaceMedium),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column {
                        BasicTextField(
                            value = selectedAmount,
                            onValueChange ={ newValue ->
                                //allow only number and point
                                val filteredValue = newValue.filter { it.isDigit() || it == '.' }
                                if (filteredValue.count { it == '.' } <= 1 &&
                                    (filteredValue.indexOf('.') == -1 || filteredValue.substringAfter(".").length <= 1)) {
                                    selectedAmount = filteredValue
                                }
                            },
                            cursorBrush = SolidColor(colorResource(R.color.progress_color)),
                            textStyle = MaterialTheme.typography.labelSmall,
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    controller?.hide()
                                },
                                onDone = {
                                    controller?.hide()
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier.fillMaxWidth(0.2f),
                            singleLine = true,
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(0.2f),
                            color = colorResource(R.color.progress_color),
                            thickness = 2.dp
                        )
                    }
                    Spacer(Modifier.width(spacing.spaceMedium))
                    Text(
                        text = "Serving(s) Of",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(spacing.spaceMedium),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Per Unit",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )


                }


            }
        }
    }
}