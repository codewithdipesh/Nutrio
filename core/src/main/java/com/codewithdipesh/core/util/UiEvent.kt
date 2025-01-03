package com.codewithdipesh.core.util

sealed class UiEvent{
    data class Navigate(val route: String): UiEvent()
    data class showSnackBar(val msg: String): UiEvent()
    data class SuccessAndNavigate(val msg: String): UiEvent()
    data class NavigateAndPopUp(
        val route: String)
        : UiEvent()
    object NavigateUp : UiEvent()
}
