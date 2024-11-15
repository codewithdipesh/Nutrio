package com.codewithdipesh.mylibrary.remote.dto

import com.google.gson.annotations.SerializedName

data class FAT(
    val label: String?,
    @SerializedName("quantity")
    val value: Double?,
    val unit: String?
)