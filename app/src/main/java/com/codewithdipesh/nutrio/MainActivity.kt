package com.codewithdipesh.nutrio

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core_ui.NavTypes.NullableParcelableNavType
import com.codewithdipesh.nutrio.navigation.backNavigate
import com.codewithdipesh.nutrio.navigation.navigate
import com.codewithdipesh.nutrio.navigation.navigateAndPopUp
import com.codewithdipesh.nutrio.ui.SplashScreen
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
import com.codewithdipesh.tracker_domain.model.TrackableFood
import com.codewithdipesh.tracker_presentation.tracker_overview.add_edit.AddEditScreen
import com.codewithdipesh.tracker_presentation.tracker_overview.home.TrackerHome
import com.codewithdipesh.tracker_presentation.tracker_overview.home.TrackerOverviewViewModel
import com.codewithdipesh.tracker_presentation.tracker_overview.search_food.SearchFood
import com.codewithdipesh.tracker_presentation.tracker_overview.search_food.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
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
                val viewModel by viewModels<TrackerOverviewViewModel>()
                val showOnboarding by viewModel.showOnboarding.collectAsState()

                var showSplash by remember { mutableStateOf(true) }

                if(showSplash){
                    SplashScreen {
                        showSplash = false
                    }
                }
                else{
                    NavHost(
                        navController = navController,
                        startDestination = if(showOnboarding) Route.WELCOME else Route.TRACKER_OVERVIEW,
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
                                onNavigateAndPopUp = navController::navigateAndPopUp,
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
                        composable(
                            Route.ADD_EDIT_FOOD + "/{id}/{food}/{mealType}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                },
                                navArgument("food") {
                                    type = NavType.StringType
                                    nullable = true
                                },
                                navArgument("mealType") {
                                    type = NavType.StringType
                                    nullable = true
                                },
                            )
                        ) { entry ->
                            val id = entry.arguments?.getInt("id") ?: -1
                            val food = entry.arguments?.getString("food")?.let {
                                if (it == "null") null
                                else Json.decodeFromString<TrackableFood>(Uri.decode(it))
                            }
                            val mealType = entry.arguments?.getString("mealType") ?: MealType.Breakfast.name
                            val meal = MealType.fromString(mealType)
                            AddEditScreen(
                                id = id,
                                food = food,
                                mealType = meal,
                                onNavigate = navController::navigate,
                                onBackNavigate = navController::backNavigate,
                            )
                        }
                    }
                }
            }
        }
    }
}

