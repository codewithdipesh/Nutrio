package com.codewithdipesh.tracker_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutrients(
    private val _calories: Double,
    private val _carbs: Double,
    private val _protein: Double,
    private val _fat: Double,
    private val _fiber: Double
):Parcelable {
    val calories: Double get() = "%.1f".format(_calories).toDouble()
    val carbs: Double get() = "%.1f".format(_carbs).toDouble()
    val protein: Double get() = "%.1f".format(_protein).toDouble()
    val fat: Double get() = "%.1f".format(_fat).toDouble()
    val fiber: Double get() = "%.1f".format(_fiber).toDouble()
}