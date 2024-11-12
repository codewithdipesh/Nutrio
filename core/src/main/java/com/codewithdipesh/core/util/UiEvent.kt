package com.codewithdipesh.core.util

sealed class UiEvent{
    data class Navigate(val route: String): UiEvent()
    data class showSnackBar(val msg: String): UiEvent()
    data class NavigateAndPopUp(
        val route: String,
        val popUpRoute: String)
        : UiEvent()
    object NavigateUp : UiEvent()
}
