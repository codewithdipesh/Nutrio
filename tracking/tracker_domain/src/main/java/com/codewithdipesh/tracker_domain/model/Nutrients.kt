package com.codewithdipesh.tracker_domain.model

import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nutrients(
    @SerialName("calories")
    private val _calories: Double,
    @SerialName("carbs")
    private val _carbs: Double,
    @SerialName("protein")
    private val _protein: Double,
    @SerialName("fat")
    private val _fat: Double,
    @SerialName("fiber")
    private val _fiber: Double
) {
    val calories: Double get() = "%.1f".format(_calories).toDouble()
    val carbs: Double get() = "%.1f".format(_carbs).toDouble()
    val protein: Double get() = "%.1f".format(_protein).toDouble()
    val fat: Double get() = "%.1f".format(_fat).toDouble()
    val fiber: Double get() = "%.1f".format(_fiber).toDouble()
}