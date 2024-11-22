package com.codewithdipesh.tracker_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrackableFood(
    val name : String,
    val nutrients : Map<Unit,Nutrients>,
):Parcelable
