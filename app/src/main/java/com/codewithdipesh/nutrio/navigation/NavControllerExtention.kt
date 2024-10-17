package com.codewithdipesh.nutrio.navigation

import androidx.navigation.NavController
import com.codewithdipesh.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}