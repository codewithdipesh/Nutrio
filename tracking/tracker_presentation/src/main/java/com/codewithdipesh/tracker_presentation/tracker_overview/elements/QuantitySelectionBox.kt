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
import androidx.compose.foundation.layout.height
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
    unit:com.codewithdipesh.tracker_domain.model.Unit,
    alignment: Alignment = Alignment.Center,
    onSave : (Double,com.codewithdipesh.tracker_domain.model.Unit)->Unit,
) {
    var selectedAmount by remember(amount) {
        mutableStateOf(amount.toString())
    }
    var selectedUnit by remember(unit) {
        mutableStateOf(unit)
    }
    var UnitBoxOpen by remember {
        mutableStateOf(false)
    }
    val controller = LocalSoftwareKeyboardController.current
    val spacing = LocalSpacing.current
    Box(
        Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.1f)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (UnitBoxOpen) UnitBoxOpen = false
                else onDismiss()
            }
            .then(modifier),
    ){
        Box(
            modifier = Modifier
                .align(alignment)
                .size(200.dp, 180.dp)
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ){
                    Text(
                        text = "Per Unit",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )
                    Column (
                        modifier = Modifier.clickable {
                            UnitBoxOpen = true
                        }
                    ){
                        Text(
                            text =
                            if(selectedUnit == com.codewithdipesh.tracker_domain.model.Unit.Gm100) "100gm"
                            else selectedUnit.displayName,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Black,
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(R.color.progress_color),
                            thickness = 2.dp
                        )
                    }

                }
                //extra space
                Spacer(Modifier.height(spacing.spaceSmall))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    //dismiss button
                    Text(
                        text = "Dismiss",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.DarkGray,
                        modifier = Modifier.clickable {
                            onDismiss()
                        }
                    )
                    Spacer(Modifier.width(spacing.spaceMedium))
                    //done button
                    Text(
                        text = "Done",
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(R.color.progress_color),
                        modifier = Modifier.clickable {
                            onSave(selectedAmount.toDouble(),selectedUnit)
                            onDismiss()
                        }
                    )

                }


            }
        }

        //the unit box
        if(UnitBoxOpen) {
            Box(modifier = Modifier
                .padding(start = spacing.spaceExtraLarge * 6f,top = spacing.spaceExtraLarge*12f)
                .width(120.dp)
                .wrapContentHeight()
                .background(Color.White)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    //do nothing means box will be opened
                }
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ){
                    arrayOf(
                        com.codewithdipesh.tracker_domain.model.Unit.Gm100,
                        com.codewithdipesh.tracker_domain.model.Unit.TeaSpoon,
                        com.codewithdipesh.tracker_domain.model.Unit.TableSpoon,
                        com.codewithdipesh.tracker_domain.model.Unit.Whole,
                        com.codewithdipesh.tracker_domain.model.Unit.Cup,
                        com.codewithdipesh.tracker_domain.model.Unit.Ounce
                    ).forEach {
                        Text(
                            text =
                            if(it == com.codewithdipesh.tracker_domain.model.Unit.Gm100) "100 gm"
                            else it.displayName,
                            style = MaterialTheme.typography.labelSmall,
                            color = if(selectedUnit == it) colorResource(R.color.progress_color) else Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceSmall)
                                .clickable {
                                    selectedUnit = it
                                    UnitBoxOpen = false
                                }
                        )
                    }

                }
            }
        }

    }
}