package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing

@Composable
fun SearchBar(
    input: String = "",
    onInputChanged: (String) -> Unit,
    onClear: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    var textInput by remember {
        mutableStateOf(input)
    }

    val spacing = LocalSpacing.current
    val controller = LocalSoftwareKeyboardController.current

   Box(modifier =modifier
       .fillMaxWidth()
       .height(50.dp)
       .clip(RoundedCornerShape(26.dp))
       .border(
           width = 1.dp,
           color = colorResource(R.color.progress_color),
           shape = RoundedCornerShape(26.dp)
       ),
       contentAlignment = Alignment.Center
   ){
       Row (
           modifier = Modifier.fillMaxWidth()
               .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
           horizontalArrangement = Arrangement.Start,
           verticalAlignment = Alignment.CenterVertically
       ) {
           //search icon
           Icon(
               Icons.Filled.Search,
               contentDescription = "search",
               tint = colorResource(R.color.text_dark_grey),
               modifier = Modifier.size(24.dp).padding(end = spacing.spaceExtraSmall)
           )

           //textfield
           BasicTextField(
               value = textInput,
               onValueChange ={
                   textInput =it
                   onInputChanged(textInput)
               },
               cursorBrush = SolidColor(colorResource(R.color.progress_color)),
               textStyle = MaterialTheme.typography.displayMedium,
               decorationBox = { innerTextField ->
                   Box(
                       modifier = modifier.fillMaxWidth(),
                       contentAlignment = Alignment.CenterStart
                   ){
                       if(textInput.isEmpty()){
                           Text(
                               text = " Search for a food",
                               color = Color.LightGray,
                               style = MaterialTheme.typography.displayMedium,
                               textAlign = TextAlign.Start
                           )
                       }
                       innerTextField()
                   }
               },
               keyboardActions = KeyboardActions(
                   onSearch = {
                       controller?.hide()
                       onSearch()
                   },
                   onDone = {
                       controller?.hide()
                       onSearch()
                   }
               ),
               modifier = Modifier.fillMaxWidth(0.9f),
               singleLine = true,
           )
           //clearfield
           if(textInput.isNotEmpty()){
               Icon(
                   Icons.Filled.Close,
                   contentDescription = "clear text field",
                   tint = colorResource(R.color.progress_color),
                   modifier = Modifier.size(24.dp)
                       .clickable {
                           textInput = ""
                           onClear()
                       }
               )
           }
       }
   }
}