package com.example.nashkodimtrainee.model.network

import com.example.nashkodimtrainee.core.handleApi
import com.example.nashkodimtrainee.core.model.NetworkResult
import com.example.nashkodimtrainee.network.ListQueryEntity
import com.example.nashkodimtrainee.model.network.entities.ListResponceEntity
import com.example.nashkodimtrainee.model.network.entities.MarketsResponceEntity


class RetrofitGeckoApiSource(private val geckoApi: GeckoApiService): GeckoApiSource {

    override suspend fun getList(listQueryEntity: ListQueryEntity): NetworkResult<List<ListResponceEntity>>? {
        val result = handleApi {
            geckoApi.getList(listQueryEntity)
        }
        return result
    }

    override suspend fun getMarket(vsCurrency: String, perPage: Int ): NetworkResult<List<MarketsResponceEntity>>? {
        val result = handleApi {
            geckoApi.getMarket(vsCurrency, perPage)
        }
        return result
    }
}