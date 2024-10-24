package com.codewithdipesh.onboarding_presentation.activity_level

import androidx.annotation.StringRes
import com.codewithdipesh.core.domain.model.ActivityLevel

data class ActivityOptionUiState(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    val activityLevel: ActivityLevel
)
