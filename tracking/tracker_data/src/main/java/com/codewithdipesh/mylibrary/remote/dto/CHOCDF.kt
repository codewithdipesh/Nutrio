package com.codewithdipesh.mylibrary.remote.dto

import com.squareup.moshi.Json

data class CHOCDF(
    val label: String?,
    @Json(name = "quantity")
    val value: Double?,
    val unit: String?
)