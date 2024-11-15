package com.codewithdipesh.mylibrary.remote.dto

import com.google.gson.annotations.SerializedName


data class CHOCDF(
    val label: String?,
    @SerializedName("quantity")
    val value: Double?,
    val unit: String?
)