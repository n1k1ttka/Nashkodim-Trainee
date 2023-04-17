package com.example.nashkodimtrainee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.nashkodimtrainee.core.base.BaseFragment
import com.example.nashkodimtrainee.core.domain.model.Resource
import com.example.nashkodimtrainee.core.domain.model.ResourceState.*
import com.example.nashkodimtrainee.databinding.FragmentOverviewBinding
import com.example.nashkodimtrainee.model.network.GeckoApiService
import com.example.nashkodimtrainee.model.network.GeckoApiSource
import com.example.nashkodimtrainee.model.network.RetrofitClient
import com.example.nashkodimtrainee.model.network.RetrofitGeckoApiSource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class CryptoLabelFragment: BaseFragment() {

    private val geckoApiService = RetrofitClient.getInstance().create(GeckoApiService::class.java)
    private val geckoApiSource: GeckoApiSource = RetrofitGeckoApiSource(geckoApiService)

    override val viewModel: CryptoLabelViewModel by viewModels {
        CryptoLabelViewModel.factory(geckoApiSource)
    }

    private lateinit var binding: FragmentOverviewBinding
    private val adapter = CryptoItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this@CryptoLabelFragment
        binding.cryptoList.adapter = adapter

        val usdButton: Button = binding.usdChip
        val eurButton: Button = binding.eurChip
        usdButton.isSelected = true

        viewModel.items.observe(viewLifecycleOwner) { adapter.replaceItems(it) }
        viewModel.currency.observe(viewLifecycleOwner) {adapter.initCurrency(it, context)}
        viewModel.getCryptoLabels(vsCurrency = "usd", perPage = 20)
            .onEach { rendering(it) }
            .launchIn(viewModel.viewModelScope)

        usdButton.setOnClickListener {
            if (!usdButton.isSelected) {
                usdButton.isSelected = true
                eurButton.isSelected = false
                viewModel.getCryptoLabels(vsCurrency = "usd", perPage = 20)
                    .onEach { rendering(it) }
                    .launchIn(viewModel.viewModelScope)
            }
        }

        eurButton.setOnClickListener {
            if (!eurButton.isSelected) {
                eurButton.isSelected = true
                usdButton.isSelected = false
                viewModel.getCryptoLabels(vsCurrency = "eur", perPage = 20)
                    .onEach { rendering(it) }
                    .launchIn(viewModel.viewModelScope)
            }
        }

        binding.unsuccessfulResult.refreshButton.setOnClickListener {
            binding.unsuccessfulResult.errorResult.visibility = View.GONE
            usdButton.isSelected = true
            viewModel.getCryptoLabels(vsCurrency = "usd", perPage = 20)
                .onEach { rendering(it) }
                .launchIn(viewModel.viewModelScope)
        }

        return binding.root
    }

    private fun rendering(loadingState: Resource<String>) {
        when(loadingState.state){
            SUCCESS -> {
                binding.unsuccessfulResult.loadingIcon.visibility = View.GONE
                binding.unsuccessfulResult.errorResult.visibility = View.GONE
                binding.cryptoList.visibility = View.VISIBLE
            }
            LOADING -> {
                binding.unsuccessfulResult.loadingIcon.visibility = View.VISIBLE
                binding.unsuccessfulResult.errorResult.visibility = View.GONE
                binding.cryptoList.visibility = View.GONE
            }
            ERROR -> {
                binding.unsuccessfulResult.loadingIcon.visibility = View.GONE
                binding.unsuccessfulResult.errorResult.visibility = View.VISIBLE
                binding.cryptoList.visibility = View.GONE
            }
            MESSAGE -> {}
            NETWORK_ERROR -> {}
        }
    }

    companion object {
        fun newInstance() = CryptoLabelFragment()
    }
}