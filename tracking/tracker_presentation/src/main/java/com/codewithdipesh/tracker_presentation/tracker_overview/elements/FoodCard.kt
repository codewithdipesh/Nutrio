package com.codewithdipesh.tracker_presentation.tracker_overview.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.core.R
import com.codewithdipesh.tracker_presentation.tracker_overview.model.Meal
import kotlinx.coroutines.delay

@Composable
fun FoodCard(
    meal : Meal,
    listOfFoods : List<TrackedFood>,
    totalCalories : Int = 0,
    onExpandClick : (Meal)-> Unit = {},
    onAddClick : ()-> Unit={},
    onItemClick : (TrackedFood)-> Unit = {},
    color : Color = MaterialTheme.colorScheme.background
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var expanded by remember(meal.isExpanded) {
          mutableStateOf(meal.isExpanded)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceLarge)
            .padding(top = spacing.spaceMedium)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ( Title and toggle )         (totalCalorie)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                // ( Title and toggle )
                Row(Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.spaceExtraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = meal.name.asString(context),
                        color = Color.Black,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Icon(
                        imageVector =
                        if(!expanded) Icons.Filled.KeyboardArrowDown
                        else Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Expand ${meal.toString()}",
                        tint = colorResource(R.color.text_dark_grey),
                        modifier =Modifier.size(24.dp)
                            .clickable {
                                onExpandClick(meal)
                            }
                    )
                }

                //totalCalories
                Text(
                    text = totalCalories.toString(),
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )

            if(expanded){
               if(listOfFoods.isEmpty()){
                   Text(
                       text = stringResource(R.string.no_meal),
                       color = colorResource(R.color.medium_gray),
                       style = MaterialTheme.typography.displayMedium
                   )
                   LaunchedEffect(Unit) {
                       delay(2000)
                       onExpandClick(meal)
                   }

               }else{
                  listOfFoods.forEach{

                  }
               }

            }

            Row (Modifier
                .fillMaxWidth()
                .padding(top = spacing.spaceExtraSmall),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(R.string.add_food),
                    color = colorResource(R.color.progress_color),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.clickable {
                        onAddClick()
                    }
                )
            }

        }
    }
}