package com.example.nashkodimtrainee.model.network

import com.example.nashkodimtrainee.core.model.NetworkResult
import com.example.nashkodimtrainee.network.ListQueryEntity
import com.example.nashkodimtrainee.network.MarketsQueryEntity
import com.example.nashkodimtrainee.model.network.entities.ListResponceEntity
import com.example.nashkodimtrainee.model.network.entities.MarketsResponceEntity

interface GeckoApiSource {

    suspend fun getList(listQueryEntity: ListQueryEntity): NetworkResult<List<ListResponceEntity>>?
    suspend fun getMarket(vsCurrency: String, perPage: Int): NetworkResult<List<MarketsResponceEntity>>?
}