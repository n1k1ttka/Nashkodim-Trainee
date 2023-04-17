package com.example.nashkodimtrainee.model.network.entities

import com.google.gson.annotations.SerializedName

data class ListResponceEntity(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null
)