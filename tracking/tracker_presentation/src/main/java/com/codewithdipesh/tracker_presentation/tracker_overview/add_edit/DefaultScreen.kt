package com.codewithdipesh.tracker_presentation.tracker_overview.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codewithdipesh.core.R
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.AddEditTopBar
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.CustomRowWithAction
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.DailyPercentageCard
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.MacroDetails
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.PercentageDonutChart
import com.codewithdipesh.tracker_presentation.tracker_overview.elements.QuantitySelectionBox
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditEvent
import com.codewithdipesh.tracker_presentation.tracker_overview.model.AddEditState
import com.codewithdipesh.tracker_presentation.tracker_overview.model.MealSelectionBox
import com.codewithdipesh.tracker_presentation.tracker_overview.model.SearchUiEvent
import java.time.LocalDate

@Composable
fun DefaultScreen(
    modifier: Modifier = Modifier,
    foodName : String,
    date : LocalDate,
    snackbarHostState: SnackbarHostState ,
    title:String,
    mealType: MealType,
    NumberOfServings : Double ,
    ServingSize : com.codewithdipesh.tracker_domain.model.Unit ,
    onBackNavigate: () -> Unit,
    carbs : Double = 0.0,
    protein : Double = 0.0,
    fat : Double =0.0,
    calories : Double =0.0,
    dailyCarbGoal : Double =0.0,
    dailyProteinGoal : Double=0.0,
    dailyFatGoal : Double=0.0,
    dailyCalorieGoal : Double=0.0,
    isMealBoxOpen : Boolean ,
    isSizeBoxOpen : Boolean ,
    onDone : ()->Unit ,
    onEvent : (AddEditEvent)->Unit ,
) {
    val spacing = LocalSpacing.current

    val isMealSelectionBoxOpen by remember(isMealBoxOpen) {
        mutableStateOf(isMealBoxOpen)
    }
    val isSizeSelectionBoxOpen by remember(isSizeBoxOpen) {
        mutableStateOf(isSizeBoxOpen)
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
                        style = MaterialTheme.typography.displayMedium,
                        modifier =Modifier.clickable {
                            onEvent(AddEditEvent.OnToggleMeal)
                        }
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
                           style = MaterialTheme.typography.displayMedium,
                           modifier = Modifier.clickable {
                               onEvent(AddEditEvent.OnToggleSizeUnit)
                           }
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
                           style = MaterialTheme.typography.displayMedium,
                           modifier = Modifier.clickable {
                               onEvent(AddEditEvent.OnToggleSizeUnit)
                           }
                       )
                   }
               )
              HorizontalDivider(
                   color = Color.LightGray,
                   thickness = 1.dp
              )

               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .wrapContentHeight()
                       .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
               ){
                   PercentageDonutChart(
                       firstPercentage = carbs.toFloat(),
                       secondPercentage = protein.toFloat(),
                       thirdPercentage = fat.toFloat(),
                       text = calories.toInt().toString(),
                       textColor = Color.Black
                   )

                   Row(
                       modifier = Modifier
                           .align(Alignment.CenterEnd)
                           .padding(end = spacing.spaceExtraLarge),
                       horizontalArrangement = Arrangement.spacedBy(spacing.spaceExtraLarge),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       val totalMacros = carbs.toFloat() +protein.toFloat() +fat.toFloat()
                       MacroDetails(
                           percentage = (Math.round(((carbs.toFloat()/totalMacros)*100f) * 10.0) / 10.0).toFloat(),
                           text = "Carbs",
                           amount = carbs.toString(),
                           color = colorResource(R.color.carb)
                       )
                       MacroDetails(
                           percentage = (Math.round(((protein.toFloat()/totalMacros)*100f) * 10.0) / 10.0).toFloat(),
                           text = "Proteins",
                           amount = protein.toString(),
                           color = colorResource(R.color.protein)
                       )
                       MacroDetails(
                           percentage = (Math.round(((fat.toFloat()/totalMacros)*100f) * 10.0) / 10.0).toFloat(),
                           text = "Fat",
                           amount = fat.toString(),
                           color = colorResource(R.color.fat)
                       )
                   }
               }
              HorizontalDivider(
                  color = Color.LightGray,
                  thickness = 1.dp
              )

              Column (
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = spacing.spaceMedium),
                  horizontalAlignment = Alignment.Start,
                  verticalArrangement = Arrangement.Center
              ){
                  //Your Daily GoalPercentage
                  Text(
                      text = "Your Daily Percentage",
                      color = Color.Black,
                      style = MaterialTheme.typography.labelMedium
                  )
                  Spacer(modifier = Modifier.height(spacing.spaceMedium))
                  Row (
                      horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                  ){
                      DailyPercentageCard(
                          name = "Calorie",
                          amount = calories,
                          width = 80.dp,
                          total = dailyCalorieGoal,
                          color = colorResource(R.color.progress_color)
                      )
                      DailyPercentageCard(
                          name = "Carb",
                          amount = carbs,
                          width = 80.dp,
                          total = dailyCarbGoal,
                          color = colorResource(R.color.carb)
                      )
                      DailyPercentageCard(
                          name = "Protein",
                          amount = protein,
                          width = 80.dp,
                          total = dailyProteinGoal,
                          color = colorResource(R.color.protein)
                      )
                      DailyPercentageCard(
                          name = "Fat",
                          amount = fat,
                          width = 80.dp,
                          total = dailyFatGoal,
                          color = colorResource(R.color.fat)
                      )
                  }

              }

           }
           //Action Boxes
           if(isMealSelectionBoxOpen){
               MealSelectionBox(
                onDismiss = {
                    onEvent(AddEditEvent.OnToggleMeal)
                },
                onSelection = {meal->
                    onEvent(AddEditEvent.OnClickMeal(meal))
                },
                alignment = Alignment.TopEnd,
                modifier = Modifier.padding(top = spacing.spaceExtraLarge*2f, end = spacing.spaceMedium )
               )
           }
           //TODO SERVING SIZE
           if(isSizeSelectionBoxOpen){
               QuantitySelectionBox(
                   onDismiss = {
                       onEvent(AddEditEvent.OnToggleSizeUnit)
                   },
                   amount = NumberOfServings,
                   unit = ServingSize,
                   alignment = Alignment.Center,
                   onSave = {amount,unit ->
                       onEvent(AddEditEvent.OnClickSize(amount))
                       onEvent(AddEditEvent.OnSelectUnit(unit))
                   },
                   modifier = Modifier.padding()
               )
           }
       }

    }
}