package com.example.nashkodimtrainee.model.network

import com.example.nashkodimtrainee.network.ListQueryEntity
import com.example.nashkodimtrainee.network.MarketsQueryEntity
import com.example.nashkodimtrainee.model.network.entities.ListResponceEntity
import com.example.nashkodimtrainee.model.network.entities.MarketsResponceEntity
import com.example.nashkodimtrainee.network.MarketsQueryEntityTest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GeckoApiService {

    @Headers("Content-Type: application/json")
    @GET("coins/list")
    suspend fun getList(@Query("include_platform") listQueryEntity: ListQueryEntity): Response<List<ListResponceEntity>>

    @Headers("Content-Type: application/json")
    @GET("coins/markets")
    suspend fun getMarket(@Query("vs_currency") vsCurrency: String, @Query("per_page") perPage: Int): Response<List<MarketsResponceEntity>>
}