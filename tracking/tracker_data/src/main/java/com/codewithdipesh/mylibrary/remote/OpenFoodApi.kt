package com.codewithdipesh.mylibrary.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodApi {

    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun searchFood(
        @Query("search_items") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    )

    companion object{
        const val BASE_URL = "https://in.openfoodfacts.org/"
    }
}