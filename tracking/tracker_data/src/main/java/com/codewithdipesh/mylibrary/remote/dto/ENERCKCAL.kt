package com.codewithdipesh.mylibrary.remote.dto

import com.google.gson.annotations.SerializedName


data class ENERCKCAL(
    val label: String?,
    @SerializedName("quantity")
    val value: Double?,
    val unit: String?
)