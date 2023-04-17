package com.example.nashkodimtrainee.network

import com.google.gson.annotations.SerializedName

data class MarketsQueryEntity(
    @SerializedName("vs_currency")
    val vsCurrency: String? = null,
    @SerializedName("per_page")
    val perPage: Int? = 1
)

data class MarketsQueryEntityTest(
    @SerializedName("vs_currency")
    val vsCurrency: String? = null,
    @SerializedName("ids")
    val ids: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("order")
    val order: String? = null,
    @SerializedName("per_page")
    val perPage: Int? = 20,
    @SerializedName("page")
    val page: Int? = 1,
    @SerializedName("sparkline")
    val sparkline: Boolean? = false,
    @SerializedName("price_change_percentage")
    val priceCP: String? = null,
    @SerializedName("locale")
    val locale: String? = "en"
)

data class ListQueryEntity(
    @SerializedName("include_platform")
    val includePlatform: Boolean? = null
)