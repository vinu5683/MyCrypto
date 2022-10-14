package com.juno.mycrypto.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juno.mycrypto.mvvm.modelclasses.EmptyStateResponse
import com.juno.mycrypto.mvvm.repository.CryptoRepository
import com.juno.mycrypto.utils.ResourceWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoEmptyStateViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) :
    ViewModel() {
    private var _cryptoEmptyStateData = MutableLiveData<ResourceWrapper<EmptyStateResponse>>()
    var cryptoEmptyStateData: LiveData<ResourceWrapper<EmptyStateResponse>> = _cryptoEmptyStateData

    fun getEmptyStateData() {
        viewModelScope.launch {
            _cryptoEmptyStateData.postValue(
                ResourceWrapper.success(
                    data = cryptoRepository.getMyEmptyStateData()
                )
            )
        }
    }
}