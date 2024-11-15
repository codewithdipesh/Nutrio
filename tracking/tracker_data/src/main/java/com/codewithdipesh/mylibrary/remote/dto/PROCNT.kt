package com.codewithdipesh.mylibrary.remote.dto

import com.google.gson.annotations.SerializedName

data class PROCNT(
    val label: String?,
    @SerializedName("quantity")
    val value: Double?,
    val unit: String?
)