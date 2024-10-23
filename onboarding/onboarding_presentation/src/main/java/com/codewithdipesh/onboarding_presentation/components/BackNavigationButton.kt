package com.codewithdipesh.onboarding_presentation.components


import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.codewithdipesh.core.R
import com.codewithdipesh.core.util.UiEvent

@Composable
fun BackNavigationButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = {
        onBackClick()
    },
        modifier = modifier) {
        Icon(
            painterResource(R.drawable.back_arrow_svg),
            contentDescription = "back button"
        )
    }
}