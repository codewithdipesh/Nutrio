package com.codewithdipesh.mylibrary.remote.dto

import com.codewithdipesh.mylibrary.remote.ApiConstants

data class SearchRequest(
    val title: String = "Searching",
    val ingr: Array<String>,
    val url: String = ApiConstants.UNIT_URL
)
