package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.UiEvent

@Composable
fun BackNavigationButton(
    onBackClick: () -> Unit,
) {
    IconButton(onClick = {
        onBackClick()
    }) {
        Icon(
            painterResource(R.drawable.back_arrow_svg),
            contentDescription = "back button"
        )
    }
}