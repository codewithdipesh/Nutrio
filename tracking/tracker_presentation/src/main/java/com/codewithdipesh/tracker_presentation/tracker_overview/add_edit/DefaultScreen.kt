package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.AddEditTopBar
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CustomRowWithAction
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUiEvent
import java.time.LocalDate

@Composable
fun DefaultScreen(
    modifier: Modifier = Modifier,
    foodName : String,
    date : LocalDate,
    title:String,
    mealType: MealType = MealType.Breakfast,
    NumberOfServings : Double = 1.0,
    ServingSize : com.codewithdipesh.tracker_domain.model.Unit = com.codewithdipesh.tracker_domain.model.Unit.Gm100,
    onBackNavigate: () -> Unit,
    carbs : Double = 0.0,
    protein : Double = 0.0,
    fat : Double =0.0,
    calories : Double =0.0,
    dailyCarbGoal : Double =0.0,
    dailyProteinGoal : Double=0.0,
    dailyFatGoal : Double=0.0,
    dailyCalorieGoal : Double=0.0,
    onDone : ()->Unit = {}
) {
    val spacing = LocalSpacing.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            //<- add food      */
            AddEditTopBar(
                onBackNavigate =onBackNavigate,
                onDone = onDone,
                title = title
            )
        }
    ) {
       Box(
           modifier = Modifier
               .fillMaxSize()
               .padding(it)
       ) {
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(vertical = spacing.spaceSmall)
           ) {
               //coconut(food name)
              Text(
                  text = foodName,
                  color = Color.Black,
                  style = MaterialTheme.typography.titleMedium,
                  modifier = Modifier.padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium)
              )
              HorizontalDivider(
                   color = Color.LightGray,
                   thickness = 2.dp
              )
              //MealType ROW
              CustomRowWithAction(
                titleContent = {
                    Text(
                        text = "Meal",
                        color = Color.Black,
                        style = MaterialTheme.typography.displayMedium,
                    )
                },
                actionContent = {
                    Text(
                        text = mealType.name,
                        color = colorResource(R.color.progress_color),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
              )
              HorizontalDivider(
                   color = Color.LightGray,
                   thickness = 1.dp
              )
              //Number of Serving
               CustomRowWithAction(
                   titleContent = {
                       Text(
                           text = "Number of Servings",
                           color = Color.Black,
                           style = MaterialTheme.typography.displayMedium
                       )
                   },
                   actionContent = {
                       Text(
                           text = NumberOfServings.toString(),
                           color = colorResource(R.color.progress_color),
                           style = MaterialTheme.typography.displayMedium
                       )
                   }
               )
              HorizontalDivider(
                   color = Color.LightGray,
                   thickness = 1.dp
              )
              //Serving sIZE
               CustomRowWithAction(
                   titleContent = {
                       Text(
                           text = "Serving Size",
                           color = Color.Black,
                           style = MaterialTheme.typography.displayMedium
                       )
                   },
                   actionContent = {
                       Text(
                           text = if(ServingSize == com.codewithdipesh.tracker_domain.model.Unit.Gm100) "100g" else ServingSize.name,
                           color = colorResource(R.color.progress_color),
                           style = MaterialTheme.typography.displayMedium
                       )
                   }
               )
              HorizontalDivider(
                   color = Color.LightGray,
                   thickness = 1.dp
              )




           }
       }

    }
}