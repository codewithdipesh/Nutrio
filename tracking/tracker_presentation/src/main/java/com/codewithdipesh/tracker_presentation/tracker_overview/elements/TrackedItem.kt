package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.core.R
import java.util.Locale

@Composable
fun TrackedItem(
    modifier: Modifier = Modifier,
    item : TrackedFood,
    style : TextStyle = MaterialTheme.typography.displayMedium,
    onDeleteClick : () -> Unit = {},
    onClick : (Int) -> Unit = {},
) {
    val spacing = LocalSpacing.current
    var itemUnitAmount = item.amount
    val itemUnit = if(item.unit == com.codewithdipesh.tracker_domain.model.Unit.Gm100){
        itemUnitAmount *= 100
        "gm"
    }else{
        item.unit.name
    }
   Row (
       modifier = modifier
           .fillMaxWidth()
           .wrapContentHeight()
           .clickable {
               onClick(item.id ?: -1)
           }
           .padding(vertical = spacing.spaceSmall ),
       horizontalArrangement = Arrangement.SpaceBetween,
       verticalAlignment = Alignment.Top
   ){
       //cucumber
       //1.0 cup
       Column(
           modifier = Modifier.weight(1f)
               .fillMaxWidth(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.Start
       ) {
           Text(
               text = item.name.capitalize(Locale.ENGLISH),
               style = style,
               color = colorResource(R.color.dark_gray)
           )
           Text(
               text = "$itemUnitAmount $itemUnit",
               style = style,
               color = colorResource(R.color.medium_gray)
           )
       }
       //amount
       Text(
           text = "${item.calories}",
           style = style,
           color = colorResource(R.color.medium_gray)
       )


   }
}