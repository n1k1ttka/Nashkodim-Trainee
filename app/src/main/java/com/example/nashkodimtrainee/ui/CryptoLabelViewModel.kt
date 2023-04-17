package com.example.nashkodimtrainee.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nashkodimtrainee.core.base.BaseViewModel
import com.example.nashkodimtrainee.core.domain.model.Resource
import com.example.nashkodimtrainee.core.domain.model.ResourceState
import com.example.nashkodimtrainee.core.model.NetworkResult
import com.example.nashkodimtrainee.core.model.onError
import com.example.nashkodimtrainee.core.model.onSuccess
import com.example.nashkodimtrainee.model.network.GeckoApiSource
import com.example.nashkodimtrainee.model.network.entities.MarketsResponceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class CryptoLabelViewModel(
    private val geckoApiSource: GeckoApiSource
) : BaseViewModel() {

    private val _items = MutableLiveData<List<MarketsResponceEntity>>()
    val items: LiveData<List<MarketsResponceEntity>> = _items

    private val _currency = MutableLiveData<String>()
    val currency: LiveData<String> = _currency

    fun getCryptoLabels(vsCurrency: String, perPage: Int): Flow<Resource<String>> = flow {
        emit(Resource(ResourceState.LOADING))
        try{
            val data = withContext(Dispatchers.IO) {
                geckoApiSource.getMarket(vsCurrency = vsCurrency, perPage = perPage)
            }

            when (data) {
                is NetworkResult.Success ->
                {
                    data.onSuccess().apply {
                        emit(Resource(ResourceState.SUCCESS))
                        _items.value = this
                        _currency.value = vsCurrency
                    }
                }
                is NetworkResult.Error ->
                {
                    data.onError().apply {
                        emit(Resource(ResourceState.ERROR))
                    }

                }
                is NetworkResult.ErrorServer -> {}
                is NetworkResult.Pending -> {}
                null -> {}
            }

        } catch (e: Throwable){
            emit(Resource(ResourceState.ERROR))
        }
    }

    companion object {
        fun factory(
            geckoApiSource: GeckoApiSource
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CryptoLabelViewModel(geckoApiSource) as T
            }
        }
    }
}