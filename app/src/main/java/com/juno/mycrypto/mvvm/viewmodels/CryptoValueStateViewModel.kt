package com.juno.mycrypto.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juno.mycrypto.mvvm.modelclasses.ValueStateResponse
import com.juno.mycrypto.mvvm.repository.CryptoRepository
import com.juno.mycrypto.utils.ResourceWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CryptoValueStateViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : ViewModel() {

    private var _cryptoValueStateData = MutableLiveData<ResourceWrapper<ValueStateResponse>>()
    var cryptoValueStateData: LiveData<ResourceWrapper<ValueStateResponse>> = _cryptoValueStateData

    fun getValueStateData() {
        viewModelScope.launch {
            _cryptoValueStateData.postValue(
                ResourceWrapper.success(
                    data = cryptoRepository.getMyValueStateData()
                )
            )
        }
    }
}