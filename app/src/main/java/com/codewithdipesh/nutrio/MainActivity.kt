package com.codewithdipesh.nutrio

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.nutrio.navigation.backNavigate
import com.codewithdipesh.nutrio.navigation.navigate
import com.codewithdipesh.nutrio.navigation.navigateAndPopUp
import com.codewithdipesh.nutrio.ui.theme.NutrioTheme
import com.codewithdipesh.onboarding_presentation.activity_level.ActivityScreen
import com.codewithdipesh.onboarding_presentation.age.AgeScreen
import com.codewithdipesh.onboarding_presentation.calorie_goal.NutritionGoalScreen
import com.codewithdipesh.onboarding_presentation.gender.GenderScreen
import com.codewithdipesh.onboarding_presentation.goal.GoalScreen
import com.codewithdipesh.onboarding_presentation.goalPace.GoalPaceScreen
import com.codewithdipesh.onboarding_presentation.height.HeightScreen
import com.codewithdipesh.onboarding_presentation.weight.WeightScreen
import com.codewithdipesh.onboarding_presentation.welcome.WelcomeScreen
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_presentation.tracker_overview.add_edit.AddEditScreen
import com.codewithdipesh.tracker_presentation.tracker_overview.home.TrackerHome
import com.codewithdipesh.tracker_presentation.tracker_overview.search_food.SearchFood
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutrioTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.TRACKER_OVERVIEW,
                    // Default animations for all screens
                    enterTransition = {
                        fadeIn(animationSpec = tween(100))
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(100))
                    },
                    popEnterTransition = {
                        fadeIn(animationSpec = tween(100))
                    },
                    popExitTransition = {
                        fadeOut(animationSpec = tween(100))
                    },
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ){
                    composable(Route.WELCOME){
                       WelcomeScreen(onNavigate = navController::navigate)
                    }
                    composable(Route.GENDER){
                       GenderScreen(
                           onNavigate = navController::navigate,
                           onBackNavigate = navController::backNavigate
                           )
                    }
                    composable(Route.AGE){
                        AgeScreen(
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )
                    }
                    composable(Route.HEIGHT){
                        HeightScreen(
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )
                    }
                    composable(Route.WEIGHT){
                        WeightScreen(
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )
                    }
                    composable(Route.ACTIVITY){
                        ActivityScreen(
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )
                    }
                    composable(Route.GOAL){
                       GoalScreen(
                           onNavigate = navController::navigate,
                           onBackNavigate = navController::backNavigate
                       )
                    }
                    composable(Route.WEIGHTPACE){
                        GoalPaceScreen(
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )
                    }
                    composable(Route.NUTRIENT_GOAL){
                        NutritionGoalScreen(
                            onNavigate = navController::navigateAndPopUp,
                        )
                    }
                    composable(Route.TRACKER_OVERVIEW){
                        TrackerHome(
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )
                    }
                    composable(
                        Route.SEARCH+"/{mealName}/{date}",
                        arguments = listOf(
                            navArgument("mealName"){
                                type = NavType.StringType
                                nullable = false
                                defaultValue = MealType.Breakfast.name
                            },
                            navArgument("date"){
                                type = NavType.StringType
                                nullable = false
                                defaultValue = LocalDate.now().toString()
                            }
                        )
                    ){entry ->
                        val mealName = if(entry.arguments != null) entry.arguments!!.getString("mealName") else MealType.Breakfast.name

                        val dateString = entry.arguments!!.getString("date")
                        val date = LocalDate.parse(dateString) ?: LocalDate.now()

                        SearchFood(
                            mealType = MealType.fromString(mealName!!),
                            date = date,
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )

                    }
                    composable(Route.ADD_EDIT_FOOD+"/{id}",
                        arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                                nullable = false
                                defaultValue = -1
                            }
                        )
                    ){entry->
                        val id = if(entry.arguments != null) entry.arguments!!.getInt("id") else -1

                        AddEditScreen(
                            id = id,
                            onNavigate = navController::navigate,
                            onBackNavigate = navController::backNavigate
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NutrioTheme {
        Greeting("Android")
    }
}