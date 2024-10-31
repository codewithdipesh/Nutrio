package com.codewithdipesh.mylibrary.remote

import com.codewithdipesh.mylibrary.remote.dto.SearchDto
import com.codewithdipesh.tracker_data.BuildConfig
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenFoodApi {

    @POST("nutrition-details")
    suspend fun searchFood(
        @Query("app_id") appId: String = ApiConstants.APP_ID,
        @Query("app_key") appKey: String = ApiConstants.APP_KEY,
        @Body
        title : String = "Searching",
        @Body
        ingr : Array<String>,
        @Body
        url : String = ApiConstants.UNIT_URL,

    ): SearchDto

    fun getSearchText(food : String) : Array<String>{
        return arrayOf(
            "1 tablespoon $food",
            "1 whole $food",
            "1 teaspoon $food",
            "1 ounce $food",
            "100gm $food",
            "1 cup $food",
        )
    }

}

object ApiConstants{
    const val BASE_URL = "https://api.edamam.com/api/"
    const val APP_ID = BuildConfig.APP_ID
    const val APP_KEY = BuildConfig.APP_KEY
    const val UNIT_URL = BuildConfig.URL
}