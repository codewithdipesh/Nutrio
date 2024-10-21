package com.codewithdipesh.onboarding_presentation.height

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.CmToInch
import com.codewithdipesh.core.util.InchToCm
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun CmHeightInput(
    minHeight : Int,
    maxHeight :Int,
    initialHeightInInch : Float,
    onHeightChange : (Float)->Unit,
){
    val spacing = LocalSpacing.current
    var selectedHeightInInch by remember {
        mutableStateOf(initialHeightInInch)
    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var value by remember {
            mutableStateOf(InchToCm(selectedHeightInInch).toString())
        }
        Column (
            modifier = Modifier.width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            BasicTextField(
                value = value,
                onValueChange = {
                    if(it.toInt() <= 999){
                        value =it
                        if(value != ""){//value is empty
                            if(value.toInt() >= minHeight && value.toInt() <= maxHeight){
                                selectedHeightInInch = CmToInch(value.toInt())
                                onHeightChange(selectedHeightInInch)
                            }
                        }
                    }else{
                        value = value
                    }

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(140.dp)
                    .wrapContentWidth(),
                maxLines = 1,
                textStyle = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 70.sp
                ),

                )
            Spacer(Modifier.height(spacing.spaceExtraSmall))
            Divider(
                modifier = Modifier.width(160.dp),
                thickness = 2.dp,
                color = Color.Black
            )

        }
        Spacer(Modifier.width(spacing.spaceExtraSmall))
        Text(
            text = stringResource(R.string.cm),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}