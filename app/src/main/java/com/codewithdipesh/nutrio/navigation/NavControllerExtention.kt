package com.codewithdipesh.nutrio.navigation

import androidx.navigation.NavController
import com.codewithdipesh.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}
fun NavController.backNavigate(){
    this.navigateUp()
}
fun NavController.navigateAndPopUp(event: UiEvent.NavigateAndPopUp){
    this.navigate(event.route){
        popUpTo(event.popUpRoute){
            inclusive = true
        }

    }
}